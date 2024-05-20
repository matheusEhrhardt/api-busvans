package com.m4technology.busvans.domain.exception;

import com.m4technology.busvans.domain.enums.ExceptionEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)//, reason = "Entidade não encontrada")
public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException() {
        super(ExceptionEnum.REGISTRO_NAO_ENCONTRADO.getDescricao());
    }
    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
