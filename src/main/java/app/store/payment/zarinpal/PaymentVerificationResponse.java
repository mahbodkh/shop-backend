
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
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RefID" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "status",
    "refID"
})
@XmlRootElement(name = "PaymentVerificationResponse")
public class PaymentVerificationResponse {

    @XmlElement(name = "Status")
    protected int status;
    @XmlElement(name = "RefID")
    protected long refID;

    /**
     * Gets the value of the status property.
     *
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     */
    public void setStatus(int value) {
        this.status = value;
    }

    /**
     * Gets the value of the refID property.
     *
     */
    public long getRefID() {
        return refID;
    }

    /**
     * Sets the value of the refID property.
     *
     */
    public void setRefID(long value) {
        this.refID = value;
    }

}