package app.store.web.rest.error;

public class MobileAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public MobileAlreadyUsedException() {
        super(ErrorConstants.MOBILE_ALREADY_USED_TYPE, "Mobile is already in use!", "userManagement", "mobileexists");
    }
}
