package com.javarush.cryptoanalizer.vinogradov.caesarcryptografy.exception;

public class CaesarWorkerException extends RuntimeException {
    String reason;

    public CaesarWorkerException(String reason) {
        this.reason = reason;
    }


    public CaesarWorkerException(String reason, Throwable cause) {
        super(cause);
        this.reason = reason;
    }

    public String getReason() {
        return this.reason;
    }
}
