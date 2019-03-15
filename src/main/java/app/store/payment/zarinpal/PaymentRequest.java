
package app.store.payment.zarinpal;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MerchantID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Mobile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CallbackURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "merchantID",
    "amount",
    "description",
    "email",
    "mobile",
    "callbackURL"
})
@XmlRootElement(name = "PaymentRequest")
public class PaymentRequest {

    @XmlElement(name = "MerchantID", required = true)
    protected String merchantID;
    @XmlElement(name = "Amount")
    protected int amount;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "Email", required = true)
    protected String email;
    @XmlElement(name = "Mobile", required = true)
    protected String mobile;
    @XmlElement(name = "CallbackURL", required = true)
    protected String callbackURL;

    /**
     * Gets the value of the merchantID property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMerchantID() {
        return merchantID;
    }

    /**
     * Sets the value of the merchantID property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMerchantID(String value) {
        this.merchantID = value;
    }

    /**
     * Gets the value of the amount property.
     *
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     *
     */
    public void setAmount(int value) {
        this.amount = value;
    }

    /**
     * Gets the value of the description property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the email property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the mobile property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the value of the mobile property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMobile(String value) {
        this.mobile = value;
    }

    /**
     * Gets the value of the callbackURL property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCallbackURL() {
        return callbackURL;
    }

    /**
     * Sets the value of the callbackURL property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCallbackURL(String value) {
        this.callbackURL = value;
    }

}