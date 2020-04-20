package com.userprofile.kafka;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;

//@Service
public class Consumer {

    @KafkaListener(topics = "users", groupId = "group_id")
    public void consume(String message) throws IOException {
    	System.out.println("iiiiiiiiiii--- " + message);
    }
}