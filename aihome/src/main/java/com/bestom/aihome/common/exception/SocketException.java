package com.bestom.aihome.common.exception;

public class SocketException extends Exception {

    public SocketException() {
        super();
    }

    public SocketException(String message) {
        super(message);
    }

    public SocketException(Throwable throwable) {
        super(throwable);
    }


    public SocketException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
