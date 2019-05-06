package app.store.payment.zarinpal.constants;

public class Paths {
	// zarinpal wsdl
	// https://ir.zarinpal.com/pg/services/WebGate/wsdl

	//Zarinpal
	public static final String paymentrequest = "/paymentrequest";
	public static final String paymentverification = "/paymentverification";
	public static final String $amount_$authority = "/{" + Constants.amount + "}/{" + Constants.authority + "}";
	public static final String paymentverification_$amount_$authority = paymentverification + $amount_$authority;


}