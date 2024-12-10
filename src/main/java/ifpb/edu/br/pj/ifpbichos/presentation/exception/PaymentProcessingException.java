package ifpb.edu.br.pj.ifpbichos.presentation.exception;

public class PaymentProcessingException extends Throwable {
    public PaymentProcessingException(String message, Exception e) {
        super(message);
    }
}
