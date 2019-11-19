package ru.yusdm.shop.integration.fixer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CurrencyRatesHolder {
    private Map<String, Double> rates;
}
