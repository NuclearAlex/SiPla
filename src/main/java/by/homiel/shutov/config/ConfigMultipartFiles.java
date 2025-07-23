package by.homiel.shutov.config;

import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.unit.DataSize;

@Configuration
public class ConfigMultipartFiles {

    @Primary
    @Bean
    @ConfigurationProperties("spring.servlet.multipart")
    public MultipartProperties multipartFileConfig() {
        MultipartProperties multipartProperties = new MultipartProperties();
        multipartProperties.setMaxFileSize(DataSize.ofBytes(-1));
        multipartProperties.setMaxRequestSize(DataSize.ofBytes(-1));
        return multipartProperties;
    }
}
