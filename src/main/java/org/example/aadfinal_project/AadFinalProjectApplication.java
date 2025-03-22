package org.example.aadfinal_project;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "org.example.aadfinal_project")
public class AadFinalProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(AadFinalProjectApplication.class, args);
    }
}

