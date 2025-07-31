package by.homiel.shutov.sipla_web.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.Connection;
import java.sql.SQLException;

import static by.homiel.shutov.sipla_web.utils.Constants.MONGO_PREFIX;

@Configuration
public class ConfigDb {
    @Value("${separator}")
    public String separator;
    @Value("${spring.datasource.url}")
    private String postUrl;
    @Value("${spring.datasource.username}")
    private String postUser;
    @Value("${spring.datasource.password}")
    private String postPassword;
    @Value("${spring.datasource.driver-class-name}")
    private String postDriver;

    @Value("${spring.data.mongodb.host}")
    private String mongoUrl;
    @Value("${spring.data.mongodb.username}")
    private String mongoUser;
    @Value("${spring.data.mongodb.password}")
    private String mongoPassword;


    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public Connection getPostgresqlConnection() throws SQLException {
        return DataSourceBuilder
                .create()
                .url(postUrl)
                .username(postUser)
                .password(postPassword)
                .driverClassName(postDriver)
                .build()
                .getConnection();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb")
    public MongoClient getMongodbClient() {
        String connectionString = String.format("%s%s:%s@%s", MONGO_PREFIX, mongoUser, mongoPassword, mongoUrl);
        return MongoClients.create(connectionString);
    }
}
