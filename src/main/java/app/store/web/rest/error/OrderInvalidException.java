package app.store.web.rest.error;

public class OrderInvalidException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public OrderInvalidException() {
        super(ErrorConstants.ORDER_INVALID_DATA, "Order is wrong filled!", "orderManagement", "orderinvalid");
    }

}
