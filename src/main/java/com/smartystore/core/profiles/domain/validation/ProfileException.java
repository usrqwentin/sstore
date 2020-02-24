package com.smartystore.core.profiles.domain.validation;

import com.smartystore.core.common.api.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ProfileException extends ValidationException {

    private ProfileException(String message, int id) {
        super(message, "profile", id);
    }

    public static ProfileException create(String message, int id) {
        return new ProfileException(message, id);
    }
}
