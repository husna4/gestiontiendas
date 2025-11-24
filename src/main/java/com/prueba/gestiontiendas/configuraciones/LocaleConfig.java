package com.prueba.gestiontiendas.configuraciones;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

/**
 * @author Husnain
 */

@Configuration
public class LocaleConfig {

    private static final String IDIOMA_POR_DEFECTO = "es";
    private static final String ENCODING = "UTF-8";
    private static final String NOMBRE_BASE_ARCHIVO_MENSAJES = "messages";

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.of(IDIOMA_POR_DEFECTO));
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(NOMBRE_BASE_ARCHIVO_MENSAJES);
        messageSource.setDefaultEncoding(ENCODING);
        return messageSource;
    }
}
