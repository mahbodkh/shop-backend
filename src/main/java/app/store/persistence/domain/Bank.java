package app.store.persistence.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

@org.springframework.data.mongodb.core.mapping.Document(collection = "bank")
public class Bank extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;
    @Field
    private String name;                        // name of the bank , mellat , passargad ,
    @Field
    private String merchant;                    // some banks need merchant id for detect you
    @Field
    private String terminalId;                  // some banks need terminal id for detect you
    @Field
    private String gatewayUrl;                  // the main uri for payment
    @Field
    private String username;                    // the username
    @Field
    private String password;                    // the password
    @Field
    private String mobile;                      // the mobile number of you account
    @Field
    private String gatewayPostfix;              // the main postfix
    @Field
    private String gatewayPostfixMobile;        // the postfix when your client use the mobile
    @Field
    private String gatewayPostfixWeb;           // the postfix when your client use the web / browser


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(id, bank.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", merchant='" + merchant + '\'' +
                ", terminalId='" + terminalId + '\'' +
                ", gatewayUrl='" + gatewayUrl + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gatewayPostfix='" + gatewayPostfix + '\'' +
                ", gatewayPostfixMobile='" + gatewayPostfixMobile + '\'' +
                ", gatewayPostfixWeb='" + gatewayPostfixWeb + '\'' +
                '}';
    }
}
