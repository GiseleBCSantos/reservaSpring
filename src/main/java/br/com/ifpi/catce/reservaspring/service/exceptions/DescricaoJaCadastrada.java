package br.com.ifpi.catce.reservaspring.service.exceptions;

public class DescricaoJaCadastrada extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DescricaoJaCadastrada(String message) {
        super(message);
    }
}
