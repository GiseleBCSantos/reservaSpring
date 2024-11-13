package br.com.ifpi.catce.reservaspring.service.exceptions;

public class EmailJaCadastrado extends RuntimeException {
    public EmailJaCadastrado(String message) {
        super(message);
    }
}
