package com.gusta.mercadolivre.compartilhado.errorhandler;

public class FieldErrorOutputDTO {

    private String field;
    private String errorMessage;

    public FieldErrorOutputDTO(String field, String errorMessage) {
        this.field = field;
        this.errorMessage = errorMessage;
    }

    public String getField() {
        return field;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
