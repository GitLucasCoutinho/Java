package org.example.br.com.ocooldev.exception;
// Quando o email informado não é válido
public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(final String message) {
        super("<-ERROR-> " + message);
    }
}