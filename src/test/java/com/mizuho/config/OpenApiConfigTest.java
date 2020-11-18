package com.mizuho.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenApiConfigTest {

    @Test
    void priceServiceOpenAPI() {
        OpenApiConfig openApiConfig = new OpenApiConfig();
        assertTrue(openApiConfig.priceServiceOpenAPI() instanceof OpenAPI);
        assertEquals("Price Service API", openApiConfig.priceServiceOpenAPI().getInfo().getTitle());
    }
}