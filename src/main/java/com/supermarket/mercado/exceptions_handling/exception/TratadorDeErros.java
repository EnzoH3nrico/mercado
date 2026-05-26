package com.supermarket.mercado.exceptions_handling.exception;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity erro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity erro400(MethodArgumentNotValidException ex){
        var erro = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erro.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(MercadoException.class)
    public ResponseEntity<String> mercadoException(MercadoException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    private record DadosErroValidacao(String campo, String mensagem){

        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
