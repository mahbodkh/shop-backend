package app.store.web.rest.error;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class KeywordNotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public KeywordNotFoundException() {
        super(ErrorConstants.KEYWORD_NOT_FOUND_TYPE, "Keyword not registered", Status.BAD_REQUEST);
    }
}
