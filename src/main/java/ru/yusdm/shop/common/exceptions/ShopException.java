package ru.yusdm.shop.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ShopException extends RuntimeException {
    private final ExceptionCode exceptionCode;
}
