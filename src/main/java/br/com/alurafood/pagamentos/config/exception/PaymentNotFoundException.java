package br.com.alurafood.pagamentos.config.exception;

public class PaymentNotFoundException extends RuntimeException {

    public PaymentNotFoundException(Long id) {
        super(String.format("User with id %d not found.", id));
    }
}