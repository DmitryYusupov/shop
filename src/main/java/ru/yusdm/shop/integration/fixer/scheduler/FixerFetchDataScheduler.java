package ru.yusdm.shop.integration.fixer.scheduler;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.yusdm.shop.integration.fixer.api.rest.FixerRestClient;

@Component
@AllArgsConstructor
public class FixerFetchDataScheduler {

    private final FixerRestClient fixerRestClient;

    @Scheduled(cron = "${app.integrations.fixer.cron}")
    public void updateCurrencyDataCache() {
        fixerRestClient.fetchCurrencyData();
    }

}
