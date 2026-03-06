package org.example.br.com.ocooldev.exception;

// Quando o formato da data de nascimento está inválido
public class InvalidBirthdayFormatException extends RuntimeException {
    public InvalidBirthdayFormatException(final String message) {
        super("<-ERROR-> " + message);
    }
}