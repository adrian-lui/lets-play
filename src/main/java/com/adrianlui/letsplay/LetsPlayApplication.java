package com.adrianlui.letsplay;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class LetsPlayApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(LetsPlayApplication.class, args);


	}
}
