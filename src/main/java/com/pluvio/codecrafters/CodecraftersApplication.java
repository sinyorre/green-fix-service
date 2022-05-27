package com.pluvio.codecrafters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CodecraftersApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodecraftersApplication.class, args);
    }

}
