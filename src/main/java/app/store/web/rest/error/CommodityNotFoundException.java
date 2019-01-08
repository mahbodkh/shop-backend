package app.store.web.rest.error;

public class CommodityNotFoundException  extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public CommodityNotFoundException() {
        super(ErrorConstants.COMMODITY_NOT_FOUND_TYPE, "Commodity not registered!", "commodityManagement", "commoditynotfound");
    }
}
