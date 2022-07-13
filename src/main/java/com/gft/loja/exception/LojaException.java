package com.gft.loja.exception;

public class LojaException extends RuntimeException{

    //falta serial

    private String message;

    public LojaException(String mensagem) {
        super(mensagem);
        this.message = mensagem;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
