package br.com.ifpi.catce.reservaspring.service.exceptions;

public class NomeJaCadastrado extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NomeJaCadastrado(String message) {
        super(message);
    }
}
