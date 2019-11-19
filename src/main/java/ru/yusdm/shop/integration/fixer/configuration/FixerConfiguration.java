package ru.yusdm.shop.integration.fixer.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.yusdm.shop.common.domain.Currency;

@Configuration
@ConfigurationProperties(prefix = "app.integrations.fixer")
@Getter
@Setter
public class FixerConfiguration {
    private String accessKey;
    private String url;
    private Currency baseCurrency;
    private String cron;
}
