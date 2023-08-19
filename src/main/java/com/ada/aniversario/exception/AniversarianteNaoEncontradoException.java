package com.ada.aniversario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Aniversariante não encontrado")
public class AniversarianteNaoEncontradoException extends RuntimeException {
    public AniversarianteNaoEncontradoException(String msg) {
        super(msg);
    }
}
