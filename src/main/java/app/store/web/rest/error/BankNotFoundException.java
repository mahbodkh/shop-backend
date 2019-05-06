package app.store.web.rest.error;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class BankNotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public BankNotFoundException() {
        super(ErrorConstants.BANK_NOT_FOUND_TYPE, "PaymentMethod not Found", Status.BAD_REQUEST);
    }
}
