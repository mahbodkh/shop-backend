package app.store.payment.domain;


public class PaymentVerificationDto {

    public int amount;
    public String authority;

    public PaymentVerificationDto() {
    }

    public PaymentVerificationDto(int amount, String authority) {
        this.amount = amount;
        this.authority = authority;
    }

    // -- getters
    public int getAmount() {
        return amount;
    }

    public String getAuthority() {
        return authority;
    }

    // --setters
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}