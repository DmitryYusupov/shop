package ru.yusdm.shop.common.configuration.database;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "app.db")
@Getter
@Setter
public class DatabaseProps {
    private String url;
    private String user;
    private String password;
    private String driver;
    private String schema;
}
