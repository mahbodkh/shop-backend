package app.store.service.dto;

public class BankDto {

    public BankDto() {
    }

    private String id;
    private String name;                        // name of the bank , mellat , passargad ,
    private String merchant;                    // some banks need merchant id for detect you
    private String terminalId;                  // some banks need terminal id for detect you
    private String username;                    // the username
    private String password;                    // the password
    private String mobile;                      //
    private String gatewayUrl;                  // the main uri for payment
    private String gatewayPostfix;              // the main postfix
    private String gatewayPostfixMobile;        // the postfix when your client use the mobile
    private String gatewayPostfixWeb;           // the postfix when your client use the web / browser

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
