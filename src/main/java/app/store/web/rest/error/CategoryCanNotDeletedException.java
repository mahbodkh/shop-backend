package app.store.web.rest.error;

public class CategoryCanNotDeletedException extends BadRequestAlertException {
    private static final long serialVersionUID = 1L;

    public CategoryCanNotDeletedException() {
        super(ErrorConstants.CATEGORY_NOT_DELETED_TYPE, "Category can not delete!", "categoryManagement", "categorydelete");
    }
}
