package br.edu.infnet.andre_gaspar_api.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class TratadorGlobalExcecoes {

    @ExceptionHandler(DadosInvalidosException.class)
    public ResponseEntity<ErroApi> tratarDadosInvalidos(
            DadosInvalidosException exception,
            HttpServletRequest request
    ) {
        return criarResposta(
                HttpStatus.BAD_REQUEST,
                exception,
                request
        );
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ErroApi> tratarEntidadeNaoEncontrada(
            EntidadeNaoEncontradaException exception,
            HttpServletRequest request
    ) {
        return criarResposta(
                HttpStatus.NOT_FOUND,
                exception,
                request
        );
    }

    @ExceptionHandler(EntidadeJaExistenteException.class)
    public ResponseEntity<ErroApi> tratarEntidadeJaExistente(
            EntidadeJaExistenteException exception,
            HttpServletRequest request
    ) {
        return criarResposta(
                HttpStatus.CONFLICT,
                exception,
                request
        );
    }

    private ResponseEntity<ErroApi> criarResposta(
            HttpStatus status,
            RuntimeException exception,
            HttpServletRequest request
    ) {
        ErroApi erro = new ErroApi(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(erro);
    }
}