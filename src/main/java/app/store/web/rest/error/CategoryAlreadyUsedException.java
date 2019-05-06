package app.store.web.rest.error;

public class CategoryAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public CategoryAlreadyUsedException() {
        super(ErrorConstants.CATEGORY_ALREADY_USED_TYPE, "Category name already used!", "categoryManagement", "categoryexists");
    }
}
