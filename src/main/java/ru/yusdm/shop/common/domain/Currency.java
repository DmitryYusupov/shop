package ru.yusdm.shop.common.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Currency {
    USD, EUR;

    static Map<String, Currency> currencyByStringValueMap = Arrays.stream(Currency.values())
            .collect(Collectors.toMap(Enum::toString, Function.identity()));

    public static Optional<Currency> getCurrencyByStringValue(String strValue) {
        return Optional.ofNullable(currencyByStringValueMap.get(strValue));
    }
}
