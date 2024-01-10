package com.javarush.cryptoanalizer.vinogradov.filesWorker.exception;

public class FileWorkerException extends RuntimeException {

    String reason;

    public FileWorkerException(String reason) {
        this.reason = reason;
    }


    public FileWorkerException(String reason, Throwable cause) {
        super(cause);
        this.reason = reason;
    }

    public String getReason() {
        return this.reason;
    }
}
