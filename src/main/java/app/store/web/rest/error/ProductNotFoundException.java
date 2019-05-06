package app.store.web.rest.error;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class ProductNotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public ProductNotFoundException() {
        super(ErrorConstants.PRODUCT_NOT_FOUND_TYPE, "Product not registered!", Status.BAD_REQUEST);
    }
}
