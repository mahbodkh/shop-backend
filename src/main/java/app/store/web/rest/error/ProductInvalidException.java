package app.store.web.rest.error;

public class ProductInvalidException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public ProductInvalidException() {
        super(ErrorConstants.PRODUCT_INVALID_DATA, "Product is wrong filled!", "productManagement", "productinvalid");
    }
}
