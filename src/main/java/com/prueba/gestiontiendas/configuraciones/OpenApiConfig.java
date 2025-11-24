package com.prueba.gestiontiendas.configuraciones;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Husnain
 */

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestión de Tiendas API")
                        .version("1.0")
                        .description("API REST para la gestión de tiendas, trabajadores, secciones y asignaciones")
                        .contact(new Contact()
                                .name("Husnain Chaudhry")));
    }
}
