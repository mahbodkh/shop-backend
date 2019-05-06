package app.store.payment.domain;


public class PaymentRequest {

    public int amount;
    public String description;
    public String callbackURL;
    public String email;
    public String mobile;

    public PaymentRequest() {
    }

    public PaymentRequest(int amount, String description) {
        this.amount = amount;
        this.description = description;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCallbackURL() {
        return callbackURL;
    }

    public void setCallbackURL(String callbackURL) {
        this.callbackURL = callbackURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}