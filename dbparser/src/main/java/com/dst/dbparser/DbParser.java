package com.dst.dbparser;

import com.dst.dbparser.locarus.DataParser;
import com.dst.dbparser.locarus.response.LocarusDataField;
import com.dst.dbparser.parsed.ParsedEntity;
import com.mongodb.client.MongoClients;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.util.CloseableIterator;

import java.util.TimerTask;

public class DbParser extends TimerTask {

    private static final String connectionStr = "mongodb://root:root1@192.168.210.235:27017/?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&ssl=false";
    private static final String dbRawName = "wheel_loaders";
    private static final String dbParsedName = "wl_parsed";

    private final MongoOperations mongoOpsRaw = new MongoTemplate(new SimpleMongoClientDatabaseFactory(MongoClients.create(connectionStr), dbRawName));
    private final MongoOperations mongoOpsParsed = new MongoTemplate(new SimpleMongoClientDatabaseFactory(MongoClients.create(connectionStr), dbParsedName));

    @Override
    public void run() {
        for (String loaderCollection : mongoOpsRaw.getCollectionNames()) {
            //Такой коллекции еще нет
            if (!mongoOpsParsed.collectionExists(loaderCollection)) {
                createNewCollection(loaderCollection);
            }
            //Коллекция есть
            else {
                addDataToCollection(loaderCollection);
            }
        }
        System.out.println("--------___--------");
    }

    private void createNewCollection(String loaderCollection) {
        System.out.println("Creating new collection...");
        mongoOpsParsed.indexOps(loaderCollection).ensureIndex(new Index().on("time", Sort.Direction.ASC).unique());
        CloseableIterator<LocarusDataField> closeableIterator = mongoOpsRaw.stream(new Query(), LocarusDataField.class, loaderCollection);
        while (closeableIterator.hasNext()) {
            mongoOpsParsed.insert(DataParser.parseLocarusDataField(closeableIterator.next()), loaderCollection);
        }
        System.out.println("Created collection: " + loaderCollection);
    }

    private void addDataToCollection(String loaderCollection) {
        System.out.println("Collection: " + loaderCollection);
        Query query = new Query().with(Sort.by(Sort.Direction.DESC, "time")).limit(1);
        String lastTimeRaw = mongoOpsRaw.find(query, LocarusDataField.class, loaderCollection).get(0).getTime();
        String lastTimeParsed = mongoOpsParsed.find(query, ParsedEntity.class, loaderCollection).get(0).getTime();
        //Время последней записи не совпадает
        if (!lastTimeRaw.equals(lastTimeParsed)) {
            Query queryGreater = new Query().addCriteria(Criteria.where("time").gt(lastTimeParsed));
            mongoOpsRaw.find(queryGreater, LocarusDataField.class, loaderCollection)
                    .stream()
                    .map(DataParser::parseLocarusDataField)
                    .forEach(o -> mongoOpsParsed.insert(o, loaderCollection));
            System.out.println("Data added");
        } else {
            System.out.println("Synchronized at: " + lastTimeParsed);
        }
    }
}
