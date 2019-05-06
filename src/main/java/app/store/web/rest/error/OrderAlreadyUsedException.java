package app.store.web.rest.error;

public class OrderAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public OrderAlreadyUsedException() {
        super(ErrorConstants.ORDER_ALREADY_USED_TYPE, "Order is already exist!", "orderManagement", "orderexists");
    }


}
