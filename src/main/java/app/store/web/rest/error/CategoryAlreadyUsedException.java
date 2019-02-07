package app.store.web.rest.error;

public class CategoryAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public CategoryAlreadyUsedException() {
        super(ErrorConstants.LOGIN_ALREADY_USED_TYPE, "Login name already used!", "userManagement", "userexists");
    }
}
