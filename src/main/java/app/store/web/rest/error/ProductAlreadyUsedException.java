package app.store.web.rest.error;

public class ProductAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public ProductAlreadyUsedException() {
        super(ErrorConstants.PRODUCT_ALREADY_USED_TYPE, "Product is already exist!", "productManagement", "productexists");
    }
}
