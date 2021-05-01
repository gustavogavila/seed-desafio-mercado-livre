package com.gusta.mercadolivre.compartilhado.errorhandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsOutputDTO {

    private final List<String> globalErrorMessages = new ArrayList<>();
    private final List<FieldErrorOutputDTO> fieldErrorMessages = new ArrayList<>();
    private final Instant timestamp = Instant.now();
    private Integer totalErros = 0;

    public void addGlobalError(String errorMessage) {
        globalErrorMessages.add(errorMessage);
        totalErros++;
    }

    public void addFieldError(String field, String errorMessage) {
        FieldErrorOutputDTO fieldError = new FieldErrorOutputDTO(field, errorMessage);
        fieldErrorMessages.add(fieldError);
        totalErros++;
    }

    public List<String> getGlobalErrorMessages() {
        return globalErrorMessages;
    }

    public List<FieldErrorOutputDTO> getFieldErrorMessages() {
        return fieldErrorMessages;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getTotalErros() {
        return totalErros;
    }
}
