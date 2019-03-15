package app.store.web.rest.error;

public class BankAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public BankAlreadyUsedException() {
        super(ErrorConstants.BANK_ALREADY_USED_TYPE, "PaymentMethod is already in use!", "bankManagement", "bankexists");
    }
}
