package app.store.web.rest.error;

public class UserAlreadyUsedException extends BadRequestAlertException {
    private static final long serialVersionUID = 1L;

    public UserAlreadyUsedException() {
        super(ErrorConstants.USER_ALREADY_USED_TYPE, "User is already exist!", "userManagement", "userexists");
    }

    public UserAlreadyUsedException(String value) {
        super(ErrorConstants.USER_ALREADY_USED_TYPE, "User is already exist: " + value , "userManagement", "userexists");
    }
}
