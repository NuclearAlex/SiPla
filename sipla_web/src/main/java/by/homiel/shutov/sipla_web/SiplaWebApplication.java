package by.homiel.shutov.sipla_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(
        exclude = MongoAutoConfiguration.class)
public class SiplaWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiplaWebApplication.class, args);
    }

}
