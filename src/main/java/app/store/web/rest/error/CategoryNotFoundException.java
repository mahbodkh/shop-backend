package app.store.web.rest.error;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class CategoryNotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public CategoryNotFoundException() {
        super(ErrorConstants.CATEGORY_NOT_FOUND_TYPE, "Category not registered", Status.BAD_REQUEST);
    }
}
