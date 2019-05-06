package app.store.web.rest.error;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class VendorNotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public VendorNotFoundException() {
        super(ErrorConstants.VENDOR_NOT_FOUND_TYPE, "Vendor not registered", Status.BAD_REQUEST);
    }
}
