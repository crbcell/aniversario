package com.ada.aniversario.validation.dto;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsOutputDto {

    List<FieldErrorOutputDto> fieldErrors = new ArrayList<>();

    public void addFieldError(String field, String msg){
        FieldErrorOutputDto fieldErrorOutputDto = new FieldErrorOutputDto(field, msg);
        fieldErrors.add(fieldErrorOutputDto);
    }

    public List<FieldErrorOutputDto> gettErros(){
        return fieldErrors;
    }

    public int getNumbersOfErrors(){
        return this.fieldErrors.size();
    }
}
