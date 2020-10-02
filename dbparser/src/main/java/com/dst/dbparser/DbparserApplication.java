package com.dst.dbparser;

import com.dst.dbparser.locarus.DataParser;
import com.dst.dbparser.locarus.response.LocarusDataField;
import com.dst.dbparser.locarus.response.Time;
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

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class DbparserApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbparserApplication.class, args);

        MongoOperations mongoOpsRaw = new MongoTemplate(new SimpleMongoClientDatabaseFactory(MongoClients.create(), "wheel_loaders"));
        MongoOperations mongoOpsParsed = new MongoTemplate(new SimpleMongoClientDatabaseFactory(MongoClients.create(), "wl_parsed"));

        for (String loaderCollection : mongoOpsRaw.getCollectionNames()) {
            //Такой коллекции еще нет
            if (!mongoOpsParsed.collectionExists(loaderCollection)) {
                mongoOpsParsed.insertAll((mongoOpsRaw.findAll(LocarusDataField.class, loaderCollection).stream().map(DataParser::parseLocarusDataField)).collect(Collectors.toList()));
                MongoNamespace namespace = new MongoNamespace("wl_parsed", loaderCollection);
                mongoOpsParsed.getCollection(ParsedEntity.class.getSimpleName().replace(ParsedEntity.class.getSimpleName().charAt(0), ParsedEntity.class.getSimpleName().toLowerCase().charAt(0))).renameCollection(namespace);
            }
            //Коллекция есть
            else {
                System.out.println("Collection exists");
                Query query = new Query().with(Sort.by(Sort.Direction.DESC, "time")).limit(1);
                Time lastTimeRaw = mongoOpsRaw.find(query, LocarusDataField.class, loaderCollection).get(0).getTime();
                System.out.println("lastTimeRaw: " + lastTimeRaw);
                Time lastTimeParsed = mongoOpsParsed.find(query, ParsedEntity.class, loaderCollection).get(0).getTime();
                System.out.println("lastTimeParsed: " + lastTimeParsed);
//                System.out.println(lastTimeRaw.compareTo(lastTimeParsed));
                //Время последней записи не совпадает
                if (!lastTimeRaw.getValue().equals(lastTimeParsed.getValue())) {
                    Query queryGreater = new Query().addCriteria(Criteria.where("time").gt(lastTimeParsed));
                    mongoOpsParsed.insert(mongoOpsRaw.find(queryGreater, LocarusDataField.class, loaderCollection).stream().map(DataParser::parseLocarusDataField).collect(Collectors.toList()), loaderCollection);
                    System.out.println("Need to insert " + mongoOpsRaw.find(queryGreater, LocarusDataField.class, loaderCollection).size() + "documents.");
                }
            }
        }
    }
}
