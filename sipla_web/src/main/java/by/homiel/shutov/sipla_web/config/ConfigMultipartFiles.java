package by.homiel.shutov.sipla_web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.unit.DataSize;

@Configuration
public class ConfigMultipartFiles {

    @Value("${spring.servlet.multipart.max-file-size}")
    private long maxFileSize;
    @Value("${spring.servlet.multipart.max-request-size}")
    private long maxRequestSize;

    @Primary
    @Bean
    @ConfigurationProperties("spring.servlet.multipart")
    public MultipartProperties multipartFileConfig() {
        MultipartProperties multipartProperties = new MultipartProperties();
        multipartProperties.setMaxFileSize(DataSize.ofBytes(maxFileSize));
        multipartProperties.setMaxRequestSize(DataSize.ofBytes(maxRequestSize));
        return multipartProperties;
    }
}
