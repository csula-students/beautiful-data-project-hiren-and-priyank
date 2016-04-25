package edu.csula.datascience.aquisition;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class mongoDB {

	MongoClient mongo = null;
	DB db = null;
	DBCollection collection = null;

	public mongoDB() {
		try {
			mongo = new MongoClient("localhost", 27017);
			db = mongo.getDB("Collector");
			collection = db.getCollection("data");
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}

}
