package com.m4technology.busvans.domain.exception;


import com.m4technology.busvans.domain.enums.ExceptionEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)//, reason = "Entidade não encontrada")
public class NegocioException extends RuntimeException {

    public NegocioException() {
        super(ExceptionEnum.ERRO.getDescricao());
    }
    public NegocioException(String message) {
        super(message);
    }
}
