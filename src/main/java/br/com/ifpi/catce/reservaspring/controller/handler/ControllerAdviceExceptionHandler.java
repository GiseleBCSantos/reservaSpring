package br.com.ifpi.catce.reservaspring.controller.handler;

import br.com.ifpi.catce.reservaspring.service.exceptions.DescricaoJaCadastrada;
import br.com.ifpi.catce.reservaspring.service.exceptions.EmailJaCadastrado;
import br.com.ifpi.catce.reservaspring.service.exceptions.NomeJaCadastrado;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

    @ExceptionHandler(NomeJaCadastrado.class)
    public ResponseEntity<String> handleNomeJaCadastrado(NomeJaCadastrado e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(EmailJaCadastrado.class)
    public ResponseEntity<String> handleEmailJaCadastrado(EmailJaCadastrado e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(DescricaoJaCadastrada.class)
    public ResponseEntity<String> handleDescricaoJaCadastrado(DescricaoJaCadastrada e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
