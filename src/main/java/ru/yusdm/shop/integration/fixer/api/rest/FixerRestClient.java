package ru.yusdm.shop.integration.fixer.api.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.yusdm.shop.common.domain.Currency;
import ru.yusdm.shop.integration.fixer.cache.DailyCurrencyByEuroRatesCache;
import ru.yusdm.shop.integration.fixer.configuration.FixerConfiguration;
import ru.yusdm.shop.integration.fixer.model.CurrencyRatesHolder;

@Component
@AllArgsConstructor
public class FixerRestClient {

    private final FixerConfiguration fixerConfiguration;

    private final RestTemplate restTemplate = new RestTemplate();

    private final DailyCurrencyByEuroRatesCache currencyByEuroRatesCache;

    public void fetchCurrencyData() {
        ResponseEntity<CurrencyRatesHolder> exchange = restTemplate.exchange(
                getServiceUrl(fixerConfiguration),
                HttpMethod.GET,
                null,
                CurrencyRatesHolder.class
        );

        boolean hasData = HttpStatus.OK == exchange.getStatusCode() && exchange.getBody() != null;
        if (hasData) {
            exchange.getBody().getRates().forEach((currencyStr, value) ->
                    Currency.getCurrencyByStringValue(currencyStr)
                            .ifPresent(currency-> currencyByEuroRatesCache.getRates().put(currency, value)));
        }
    }

    private String getServiceUrl(FixerConfiguration fixerConfiguration) {
        return fixerConfiguration.getUrl() + String.format("/latest?access_key=%s&base=%s",
                fixerConfiguration.getAccessKey(),
                fixerConfiguration.getBaseCurrency());
    }

}
