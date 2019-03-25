package app.store.web.rest.error;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class OrderNotFoundException extends AbstractThrowableProblem {
    private static final long serialVersionUID = 1L;


    public OrderNotFoundException(String value) {
        super(ErrorConstants.ORDER_NOT_FOUND_TYPE, value, Status.BAD_REQUEST);
    }

    public OrderNotFoundException() {
        super(ErrorConstants.ORDER_NOT_FOUND_TYPE, "Order not registered", Status.BAD_REQUEST);
    }

}
