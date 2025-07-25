package by.homiel.shutov.sipla_web.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(name = "sipla",
        type = SecuritySchemeType.APIKEY, bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER, paramName = "Authorization")
public class SwaggerConfig {

    private static final String TITLE = "\"Si Platform\" by Nuclearalex & Shavadre";
    private static final String DESCRIPTION =
            "Платформа для загрузки и скачивания пакетов и тем своей игры. Все права защищены ©";

    @Bean
    public OpenAPI customOpenAPI(@Value("${server.servlet.context-path}") String contextPath) {
        return new OpenAPI()
                .addServersItem(new Server().url(contextPath))
                .info(new Info()
                        .title(TITLE)
                        .description(DESCRIPTION)
                        .version("v1")
                );
    }
}
