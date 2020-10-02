package com.dst.dbparser;

import com.dst.dbparser.locarus.DataParser;
import com.dst.dbparser.locarus.response.LocarusDataField;
import com.dst.dbparser.parsed.ParsedEntity;
import com.mongodb.MongoNamespace;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class DbparserApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbparserApplication.class, args);

		MongoOperations mongoOpsRaw = new MongoTemplate(new SimpleMongoClientDatabaseFactory(MongoClients.create(), "wheel_loaders"));
		MongoOperations mongoOpsParsed = new MongoTemplate(new SimpleMongoClientDatabaseFactory(MongoClients.create(), "wl_parsed"));

		for (String loaderCollection : mongoOpsRaw.getCollectionNames()) {
			System.out.println(mongoOpsRaw.getCollection(loaderCollection).countDocuments());
			mongoOpsParsed.insertAll((mongoOpsRaw.findAll(LocarusDataField.class, loaderCollection).stream().map(DataParser::parseLocarusDataField)).collect(Collectors.toList()));
			mongoOpsParsed.getCollection(ParsedEntity.class.getSimpleName().replace(ParsedEntity.class.getSimpleName().charAt(0), ParsedEntity.class.getSimpleName().toLowerCase().charAt(0))).renameCollection(new MongoNamespace("wl_parsed", loaderCollection));
		}
	}
}
