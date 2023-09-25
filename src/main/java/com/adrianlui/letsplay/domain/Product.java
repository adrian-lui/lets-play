package com.adrianlui.letsplay.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;
@Data
@Builder
@Document(collection = "products")
public class Product {
    @MongoId
    private String id;
    private String name;
    private String description;
    private Double price;
    @DocumentReference
    private String userId;

}
