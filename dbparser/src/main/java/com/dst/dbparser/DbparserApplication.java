package com.dst.dbparser;

import com.dst.dbparser.locarus.DataParser;
import com.dst.dbparser.locarus.response.LocarusDataField;
import com.dst.dbparser.parsed.ParsedEntity;
import com.mongodb.MongoNamespace;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.stream.Collectors;

@SpringBootApplication
public class DbparserApplication {

    public static void main(String[] args) {
//        SpringApplication.run(DbparserApplication.class, args);

        MongoOperations mongoOpsRaw = new MongoTemplate(new SimpleMongoClientDatabaseFactory(MongoClients.create("mongodb://root:root@localhost:27017/?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&ssl=false"), "wheel_loaders"));
        MongoOperations mongoOpsParsed = new MongoTemplate(new SimpleMongoClientDatabaseFactory(MongoClients.create("mongodb://root:root@localhost:27017/?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&ssl=false"), "wl_parsed"));

        while (true) {
            for (String loaderCollection : mongoOpsRaw.getCollectionNames()) {
                //Такой коллекции еще нет
                if (!mongoOpsParsed.collectionExists(loaderCollection)) {
                    System.out.println("Creating new collection...");
                    mongoOpsParsed.insertAll((mongoOpsRaw.findAll(LocarusDataField.class, loaderCollection).stream().map(DataParser::parseLocarusDataField)).collect(Collectors.toList()));
                    MongoNamespace namespace = new MongoNamespace("wl_parsed", loaderCollection);
                    mongoOpsParsed.getCollection(ParsedEntity.class.getSimpleName().replace(ParsedEntity.class.getSimpleName().charAt(0), ParsedEntity.class.getSimpleName().toLowerCase().charAt(0))).renameCollection(namespace);
                    System.out.println("Created collection: " + loaderCollection);
                }
                //Коллекция есть
                else {
                    System.out.println("Collection: " + loaderCollection);
                    Query query = new Query().with(Sort.by(Sort.Direction.DESC, "time")).limit(1);
                    String lastTimeRaw = mongoOpsRaw.find(query, LocarusDataField.class, loaderCollection).get(0).getTime().getValue();
                    String lastTimeParsed = mongoOpsParsed.find(query, ParsedEntity.class, loaderCollection).get(0).getTime();
                    //Время последней записи не совпадает
                    if (!lastTimeRaw.equals(lastTimeParsed)) {
                        Query queryGreater = new Query().addCriteria(Criteria.where("time.value").gt(lastTimeParsed));
                        ArrayList<ParsedEntity> insd = (ArrayList<ParsedEntity>) mongoOpsParsed.insert(mongoOpsRaw.find(queryGreater, LocarusDataField.class, loaderCollection).stream().map(DataParser::parseLocarusDataField).collect(Collectors.toList()), loaderCollection);
                        System.out.println("Inserted " + insd.size() + " documents.");
                    }
                    else {
                        System.out.println("Synchronized at: " + lastTimeParsed);
                    }
                }
            }
            try {
                System.out.println("--------___--------");
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
