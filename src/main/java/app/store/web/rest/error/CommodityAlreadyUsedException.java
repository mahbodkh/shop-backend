package app.store.web.rest.error;

public class CommodityAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public CommodityAlreadyUsedException() {
        super(ErrorConstants.COMMODITY_ALREADY_USED_TYPE, "Product is already exist!", "commodityManagement", "commodityexists");
    }
}
