package by.homiel.shutov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class ConfigDbConnection {
    public final String separator = "";

//    @Value("${spring.datasource.url}")
//    private String url;
//    @Value("${spring.datasource.username}")
//    private String user;
//    @Value("${spring.datasource.password}")
//    private String password;

    private final String url = "jdbc:postgresql://127.0.0.1:5432/siplatdb";
    private final String user = "user";
    private final String password = "pass";


    @Bean
    @Scope("request")
    public Connection createDbConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
