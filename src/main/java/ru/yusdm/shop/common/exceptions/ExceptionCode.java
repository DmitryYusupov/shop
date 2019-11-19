package ru.yusdm.shop.common.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    NO_INFORMATION_ABOUT_CURRENCY("No information about currency!", 1);
    private String message;
    private int code;

    ExceptionCode(String message, int code) {
        this.message = message;
        this.code = code;
    }


}
