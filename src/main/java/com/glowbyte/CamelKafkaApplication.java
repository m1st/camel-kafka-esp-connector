package com.glowbyte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class CamelKafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(CamelKafkaApplication.class, args);
    }
}
