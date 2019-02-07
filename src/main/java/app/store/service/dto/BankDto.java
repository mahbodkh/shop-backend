package app.store.service.dto;

public class BankDto {

    public BankDto() {
    }

    private String name;                        // name of the bank , mellat , passargad ,
    private String merchant;                    // some banks need merchant id for detect you
    private String gatewayUrl;                  // the main uri for payment
    private String gatewayPostfix;              // the main postfix
    private String gatewayPostfixMobile;        // the postfix when your client use the mobile
    private String gatewayPostfixWeb;           // the postfix when your client use the web / browser

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }

    public String getGatewayPostfix() {
        return gatewayPostfix;
    }

    public void setGatewayPostfix(String gatewayPostfix) {
        this.gatewayPostfix = gatewayPostfix;
    }

    public String getGatewayPostfixMobile() {
        return gatewayPostfixMobile;
    }

    public void setGatewayPostfixMobile(String gatewayPostfixMobile) {
        this.gatewayPostfixMobile = gatewayPostfixMobile;
    }

    public String getGatewayPostfixWeb() {
        return gatewayPostfixWeb;
    }

    public void setGatewayPostfixWeb(String gatewayPostfixWeb) {
        this.gatewayPostfixWeb = gatewayPostfixWeb;
    }
}
