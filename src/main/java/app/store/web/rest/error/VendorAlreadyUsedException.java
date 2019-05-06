package app.store.web.rest.error;

public class VendorAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public VendorAlreadyUsedException() {
        super(ErrorConstants.VENDOR_ALREADY_USED_TYPE, "Vendor is already in use!", "vendorManagement", "vendorexists");
    }
}
