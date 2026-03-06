package org.example.br.com.ocooldev.exception;

public class UserNotFundException extends RuntimeException {
    public UserNotFundException(final String message) {
        super("<-ERROR->"+message);
    }
}
