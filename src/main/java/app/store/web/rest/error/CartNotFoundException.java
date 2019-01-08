package app.store.web.rest.error;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class CartNotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public CartNotFoundException() {
        super(ErrorConstants.CART_NOT_FOUND_TYPE, "Cart not Found", Status.BAD_REQUEST);
    }
}
