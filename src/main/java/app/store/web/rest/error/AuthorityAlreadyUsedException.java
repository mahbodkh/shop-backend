package app.store.web.rest.error;

public class AuthorityAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public AuthorityAlreadyUsedException() {
        super(ErrorConstants.AUTHORITY_ALREADY_USED_TYPE, "Authority is already in use!", "authorityManagement", "authorityexists");
    }
}
