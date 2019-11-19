package ru.yusdm.shop.integration.fixer.cache;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.yusdm.shop.common.domain.Currency;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class DailyCurrencyByEuroRatesCache {
    private Map<Currency, Double> rates = new HashMap<>();
}
