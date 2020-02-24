package com.smartystore.core.common.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException {
    private static final long serialVersionUID = 2359770307541876881L;

    private String message;
    private String delimiter;
    private int id;
}
