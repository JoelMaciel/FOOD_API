package br.com.joelmaciel.food.domain.exception;

public class FormPaymentNotFoundException  extends EntityNotExistsException{
    private static final long serialVersionUID = 1L;

    public FormPaymentNotFoundException(String message) {
        super(message);
    }

    public FormPaymentNotFoundException(Long formPaymentId) {
        this(String.format("There is no registration of payment method with code %d", formPaymentId));
    }
}
