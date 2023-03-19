package com.qr.menu.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "QR Menü Application Documentation"),
        servers = @Server(url = "http://localhost:8081")
)
public class OpenAPIConfig {

}
