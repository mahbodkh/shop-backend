package app.store.web.rest.error;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class MobileNotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public MobileNotFoundException() {
        super(ErrorConstants.MOBILE_NOT_FOUND_TYPE, "Mobile not registered", Status.BAD_REQUEST);
    }
}
