package com.riosr.dto;

import java.util.List;

public class ErrorDto {

    private List<String> fieldNames;

    private String errorMessage;

    public ErrorDto() {
    }

    public ErrorDto(List<String> fieldNames, String errorMessage) {
        this.fieldNames = fieldNames;
        this.errorMessage = errorMessage;
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
