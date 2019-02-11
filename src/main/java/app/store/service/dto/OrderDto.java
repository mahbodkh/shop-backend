package app.store.service.dto;

import java.time.Instant;
import java.util.List;

public class OrderDto {

    public OrderDto() {
    }

    private ShippingDto shippingDto;
    private String deliveryNote;
    private Instant checkout;
    private TrackingDto trackingDto;
    private PaymentDto paymentDto;
    private InvoiceDto invoiceDto;
    private List<ProductDto> productDtoList;


    public ShippingDto getShippingDto() {
        return shippingDto;
    }

    public void setShippingDto(ShippingDto shippingDto) {
        this.shippingDto = shippingDto;
    }

    public String getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    public Instant getCheckout() {
        return checkout;
    }

    public void setCheckout(Instant checkout) {
        this.checkout = checkout;
    }

    public TrackingDto getTrackingDto() {
        return trackingDto;
    }

    public void setTrackingDto(TrackingDto trackingDto) {
        this.trackingDto = trackingDto;
    }

    public PaymentDto getPaymentDto() {
        return paymentDto;
    }

    public void setPaymentDto(PaymentDto paymentDto) {
        this.paymentDto = paymentDto;
    }

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public InvoiceDto getInvoiceDto() {
        return invoiceDto;
    }

    public void setInvoiceDto(InvoiceDto invoiceDto) {
        this.invoiceDto = invoiceDto;
    }

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }
}
