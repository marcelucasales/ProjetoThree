package br.com.compasso.projetothree.controlleradvice;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import br.com.compasso.projetothree.erro.ErrorExcessao;

@RestControllerAdvice
public class ApiExceptionHandler {
    
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleAnyException(MethodArgumentNotValidException e, WebRequest request) {
        var erro =  new ErrorExcessao(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

}