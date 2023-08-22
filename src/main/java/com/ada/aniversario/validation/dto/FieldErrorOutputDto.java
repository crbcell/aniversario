package com.ada.aniversario.validation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FieldErrorOutputDto {
    private String field;
    private String msg;
}
