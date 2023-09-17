package com.adrianlui.letsplay;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class LetsPlayApplication {

	public static void main(String[] args) {
//		ConnectionString connectionString = new ConnectionString("mongodb+srv://luinairda:aMURb8nDJSAhrIVS@letsplay.bur2hzf.mongodb.net/?retryWrites=true&w=majority");
//		ServerApi serverApi = ServerApi.builder()
//				.version(ServerApiVersion.V1)
//				.build();
//		MongoClientSettings settings = MongoClientSettings.builder()
//				.applyConnectionString(connectionString)
//				.serverApi(serverApi)
//				.build();
//		// Create a new client and connect to the server
//		try (MongoClient mongoClient = MongoClients.create(settings)) {
//			try {
//				// Send a ping to confirm a successful connection
//				MongoDatabase database = mongoClient.getDatabase("admin");
//				database.runCommand(new Document("ping", 1));
//				System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
//			} catch (MongoException e) {
//				e.printStackTrace();
//			}
//		}
		SpringApplication.run(LetsPlayApplication.class, args);
	}

}
