package com.prueba.gestiontiendas.cliente.impl;

import com.prueba.gestiontiendas.cliente.TiendaExternaClient;
import com.prueba.gestiontiendas.dto.TiendaExternaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * @author Husnain
 */

@Component
public class TiendaExternaClientImpl implements TiendaExternaClient {
    private static final String STORES_PATH = "/stores";
    private static final Logger log = LoggerFactory.getLogger(TiendaExternaClientImpl.class);

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public TiendaExternaClientImpl(RestTemplate restTemplate,
                                   @Value("${tienda.externa.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public Optional<TiendaExternaDto> getTienda(String codigoTienda) {
        try {
            String url = baseUrl + "/" + STORES_PATH + "/" + codigoTienda;
            TiendaExternaDto tienda = restTemplate.getForObject(url, TiendaExternaDto.class);
            return Optional.of(tienda);
        } catch (RestClientException e) {
            log.error("Error al obtener tienda externa con código {}: {}", codigoTienda, e.getMessage());
            return Optional.empty();
        }
    }
}
