package by.homiel.shutov.sipla_web.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static by.homiel.shutov.sipla_web.utils.Constants.DESCRIPTION;
import static by.homiel.shutov.sipla_web.utils.Constants.TITLE;

@Configuration
@SecurityScheme(
        name = "sipla",
        type = SecuritySchemeType.APIKEY, bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER, paramName = "Authorization"
)
public class SwaggerConfig {
    @Value("${springdoc.api-docs.version}")
    private String version;

    @Bean
    public OpenAPI customOpenAPI(@Value("${server.servlet.context-path}") String contextPath) {
        return new OpenAPI()
                .addServersItem(new Server().url(contextPath))
                .info(new Info()
                        .title(TITLE)
                        .description(DESCRIPTION)
                        .version(version)
                );
    }
}
