package app.store.payment.enums;


public enum Return_Status_Codes_Payment {

    UNDEFINED(0, 0, "undefined"),


    //payment validation
    PAYMENT_ERROR_NOT_FOUND_BANK_CONFIG(0, 16000, "payment_error_not_found_bank_config"),
    PAYMENT_ERROR_INVALID_AMOUNT_VALUE_PAYMENT(0, 16001, "payment_error_invalid_amount_value_payment"),
    PAYMENT_ERROR_INVALID_PHONE_NUMBER(0, 16002, "payment_error_invalid_phone_number"),
    PAYMENT_ERROR_INVALID_OPERATOR(0, 16002, "payment_error_invalid_operator"),
    PAYMENT_ERROR_INVALID_EMAIL_ADDRESS(0, 16003, "payment_error_invalid_email_address"),
    PAYMENT_ERROR_SUBSCRIBER_TRANSACTION_FAILED(0, 16004, "global_subscriber_transaction_failed"),
    PAYMENT_ERROR_INVALID_SUBSCRIBER_AMOUNT(0, 16005, "payment_error_invalid_subscriber_amount"),
    PAYMENT_ERROR_NOT_FOUND_SUBSCRIBER_TRANSACTION(0, 16006, "payment_error_not_found_subscriber_transaction"),
    PAYMENT_ERROR_PAYMENT_VERIFICATION(0, 16007, "payment_error_payment_verification"),
    PAYMENT_ERROR_PAYMENT_OTHER_ERROR(0, 16008, "payment_error_payment_other_error"),
    PAYMENT_ERROR_PAYMENT_CALLBACK_USER(0, 16009, "payment_error_callback_user"),
    PAYMENT_ERROR_PAYMENT_ORDER_ID(0, 16010, "payment_error_order_id"),
    PAYMENT_ERROR_INVALID_SUBSCRIBER(0, 16011, "payment_error_invalid_subscriber"),
    PAYMENT_IS_OK(0, 16012, "payment_is_ok"),
    PAYMENT_ERROR_REQUEST_FAILED(0, 16013, "payment_error_request_failed"),
    PAYMENT_ERROR_INVALID_PARAMETER(0, 16014, "payment_error_invalid_parameter"),
    PAYMENT_ERROR_INVALID_TRANSACTION_CODE(0, 16015, "payment_error_invalid_transaction_code"),
    PAYMENT_TRANSACTION_CHECKED_BEFORE(0, 16016, "payment-transaction_checked_before"),
    PAYMENT_USER_CALLBACK_FAILED(0, 16017, "payment-user_callback_failed"),
    PAYMENT_ERROR_VERIFICATION_REQUEST_FAILED(0, 16018, "payment_error_verification_request_failed"),
    PAYMENT_ERROR_INTERNAL_ERROR(0, 16019, "payment_error_internal_error"),
    PAYMENT_ERROR_INVALID_USER_CONFIRM_CODE(0, 16020, "payment_error_invalid_user_confirm_code"),
    VALIDATE_USER_IS_OK(0, 16012, "validate_user_is_ok"),

    //mellat //PAYMENT_ERROR_MELLAT_(0, 16050, "payment_error_mellat_"),
    PAYMENT_OK_MELLAT_OK_TRANSACTION_0(0, 16050, "payment_ok_mellat_transaction_0"),//0
    PAYMENT_ERROR_MELLAT_CARD_NUMBER_INVALID_11(0, 16051, "payment_error_mellat_card_number_invalid_11"),//11
    PAYMENT_ERROR_MELLAT_NOT_ENOGH_MONEY_12(0, 16052, "payment_error_mellat_not_enogh_money_12"),
    PAYMENT_ERROR_MELLAT_PASSWORD_INVALID_13(0, 16053, "payment_error_mellat_password_invalid_13"),
    PAYMENT_ERROR_MELLAT_PASSWORD_TOO_MANY_TIMES_14(0, 16054, "payment_error_mellat_paasword_too_many_times_14"),
    PAYMENT_ERROR_MELLAT_CARD_INVALID_15(0, 16055, "payment_error_mellat_card_invalid_15"),//11
    PAYMENT_ERROR_MELLAT_GET_MONEY_TOO_MANY_TIMES_16(0, 16056, "payment_error_mellat_get_money_too_many_times_16"),
    PAYMENT_ERROR_MELLAT_USER_CANCEL_TTRANSACTION_17(0, 16057, "payment_error_mellat_user_cancel_transaction_17"),
    PAYMENT_ERROR_MELLAT_EXPIRED_CARD_DATE_18(0, 16058, "payment_error_mellat_expired_card_date_18"),
    PAYMENT_ERROR_MELLAT_LIMIT_MONEY_19(0, 16059, "payment_error_mellat_limit_money_19"),
    PAYMENT_ERROR_MELLAT_CARD_EXPORTER_INVALID_111(0, 16060, "payment_error_mellat_card_exporter_invalid_111"),
    PAYMENT_ERROR_MELLAT_SWITCH_CARD_EXPORTER_112(0, 16061, "payment_error_mellat_switch_card_exporter_112"),
    PAYMENT_ERROR_MELLAT_CARD_EXPORTER_NO_ANSWER_113(0, 16062, "payment_error_mellat_card_exporter_no_answer_113"),
    PAYMENT_ERROR_MELLAT_USER_ACCESS_114(0, 16063, "payment_error_mellat_user_access_114"),
    PAYMENT_ERROR_MELLAT_RECEIVER_INVALID_21(0, 16064, "payment_error_mellat_receiver_invalid_21"),
    PAYMENT_ERROR_MELLAT_SECURITY_23(0, 16050, "payment_error_mellat_security_23"),
    PAYMENT_ERROR_MELLAT_RECEIVER_INFORMATION_INVALID_24(0, 16065, "payment_error_mellat_receiver_information_invalid_24"),
    PAYMENT_ERROR_MELLAT_MONEY_INAVALID_25(0, 16066, "payment_error_mellat_money_invalid_25"),
    PAYMENT_ERROR_MELLAT_ANSWER_INVALID_31(0, 16067, "payment_error_mellat_answer_invalid_31"),
    PAYMENT_ERROR_MELLAT_PARAMETER_FORMAT_INVALID_32(0, 16068, "payment_error_mellat_parameter_format_invalid_32"),
    PAYMENT_ERROR_MELLAT_ACCOUNT_INVALID_33(0, 16069, "payment_error_mellat_account_invalid_33"),
    PAYMENT_ERROR_MELLAT_SYSTEM_ERROR_34(0, 16070, "payment_error_mellat_system_error_34"),
    PAYMENT_ERROR_MELLAT_DATE_INVALID_35(0, 16071, "payment_error_mellat_date_invalid_35"),
    PAYMENT_ERROR_MELLAT_REQUEST_NUMBER_REPETED_41(0, 16072, "payment_error_mellat_request_number_repeted_41"),
    PAYMENT_ERROR_MELLAT_SALE_TRANSACTION_NOT_EXIST_42(0, 16073, "payment_error_mellat_sale_transaction_not_exist_42"),
    PAYMENT_ERROR_MELLAT_VERIFY_REQUEST_REPETED_43(0, 16074, "payment_error_mellat_verify_request_repeted_43"),
    PAYMENT_ERROR_MELLAT_VERIFY_REQUEST_NOT_FOUND_44(0, 16075, "payment_error_mellat_verify_request_not_found_44"),
    PAYMENT_ERROR_MELLAT_TRANSACTION_SETTLE_45(0, 16076, "payment_error_mellat_transaction_settle_45"),
    PAYMENT_ERROR_MELLAT_TRANSACTION_NOT_SETTLE_46(0, 16077, "payment_error_mellat_transaction_not_settle_46"),
    PAYMENT_ERROR_MELLAT_TRANSACTION_SETTLE_NOT_FOUND_47(0, 16078, "payment_error_mellat_transaction_settle_not_found_47"),
    PAYMENT_ERROR_MELLAT_RECEIVE_TRANSACTION_48(0, 16079, "payment_error_mellat_receive_transaction_48"),
    PAYMENT_ERROR_MELLAT_REFUND_TRANSACTION_NOT_FOUND_49(0, 16090, "payment_error_mellat_refund_transaction_not_found_49"),
    PAYMENT_ERROR_MELLAT_BILLID_INVALID_412(0, 16091, "payment_error_mellat_billid_invalid_412"),
    PAYMENT_ERROR_MELLAT_PAYMENTID_INVALID_413(0, 16092, "payment_error_mellat_paymentid_invalid_413"),
    PAYMENT_ERROR_MELLAT_BILL_EXPORTER_INVALID_414(0, 16093, "payment_error_mellat_bill_exporter_invalid_414"),
    PAYMENT_ERROR_MELLAT_SESSION_EXPIRED_415(0, 16094, "payment_error_mellat_session_expired_415"),
    PAYMENT_ERROR_MELLAT_INFORMATION_REGISTEER_ERROR_416(0, 16095, "payment_error_mellat_information_register_error_416"),
    PAYMENT_ERROR_MELLAT_PAYERID_INVALID_417(0, 16096, "payment_error_mellat_payerid_invalid_417"),
    PAYMENT_ERROR_MELLAT_USER_INFORMATION_DEFINE_ERROR_418(0, 16097, "payment_error_mellat_user_information_define_error_418"),
    PAYMENT_ERROR_MELLAT_ENTER_INFORMATION_TOO_MANY_TIMES_419(0, 16098, "payment_error_mellat_enter_information_too_many_times_419"),
    PAYMENT_ERROR_MELLAT_IP_INVALID_421(0, 16099, "payment_error_mellat_ip_invalid_421"),
    PAYMENT_ERROR_MELLAT_TRANSACTION_REPETED_51(0, 16100, "payment_error_mellat_transaction_repeted_51"),
    PAYMENT_ERROR_MELLAT_TRANSACTION_NOT_FOUND_54(0, 16101, "payment_error_mellat_transaction_not_found_54"),
    PAYMENT_ERROR_MELLAT_TRANSATION_INVALID_55(0, 16102, "payment_error_mellat_transaction_invalid_55"),
    PAYMENT_ERROR_MELLAT_SETTLEMENT_ERROR_61(0, 16103, "payment_error_mellat_settlement_error_61"),
    PAYMENT_ERROR_MELLAT_NO_MATCH_REFID(0, 16104, "payment_error_mellat_no_match_refid"),
    PAYMENT_ERROR_MELLAT_INVALID_CALLBACK_PARAMATR(0, 16105, "payment_error_mellat_invalid_callback_parametr"),

    //zarinpal
    PAYMENT_ERROR_ZARINPAL_INVALID_REQUEST_1_(0, 16150, "payment_error_zarinpal_invalid_request_1"),
    PAYMENT_ERROR_ZARINPAL_INVALID_IP_MERCHANTID_2_(0, 16151, "payment_error_zarinpal_invalid_ip_merchantid_2"),
    PAYMENT_ERROR_ZARINPAL_AMOUNT_UNDER_T100_3_(0, 16152, "payment_error_zarinpal_amount_under_T100_3"),
    PAYMENT_ERROR_ZARINPAL_SERVICE_UNAVAILABLE_4_(0, 16153, "payment_error_zarinpal_service_unavailable_4"),
    PAYMENT_ERROR_ZARINPAL_PAYMENT_NOT_FOUND_21_(0, 16154, "payment_error_zarinpal_payment_not_found_21"),
    PAYMENT_ERROR_ZARINPAL_RESULT_WAS_FAILURE_22_(0, 16155, "payment_error_zarinpal_result_was_failure_22"),
    PAYMENT_ERROR_ZARINPAL_AMOUNT_WAS_NOT_MATCHED_33_(0, 16156, "payment_error_zarinpal_amount_was_not_matched_33"),
    PAYMENT_ERROR_ZARINPAL_RESULT_WAS_ARCHIVED_54_(0, 16157, "payment_error_zarinpal_amount_was_not_matched_54"),
    PAYMENT_ERROR_ZARINPAL_RESOURCE_NOT_FOUND_11_(0, 16158, "payment_error_zarinpal_resource_not_found_11"),
    PAYMENT_ERROR_ZARINPAL_SOAP_URL_EXCEPTION(0, 16158, "payment_error_zarinpal_soap_url_exception"),

    //tejarat
    PAYMENT_ERROR_TEJARAT_USER_CANCEL_110(0, 16200, "payment_error_tejarat_user_cancel_110"),
    PAYMENT_ERROR_TEJARAT_NO_ENOGH_MONEY_120(0, 16201, "payment_error_tejarat_no_enogh_money_120"),
    PAYMENT_ERROR_TEJARAT_LIMIT_MONEY_121(0, 16202, "payment_error_tejarat_limit_money_121"),
    PAYMENT_ERROR_TEJARAT_INVALID_CARD_INFORMATION_130(0, 16203, "payment_error_tejarat_invalid_card_information_130"),
    PAYMENT_ERROR_TEJARAT_INVALID_CARD_PASSWORD_131(0, 16204, "payment_error_tejarat_invalid_card_password_131"),
    PAYMENT_ERROR_TEJARAT_CARD_BLOCKED_132(0, 16205, "payment_error_tejarat_card_blocked_132"),
    PAYMENT_ERROR_TEJARAT_CARD_TIMEOUT_133(0, 16206, "payment_error_tejarat_card_timeout_133"),
    PAYMENT_ERROR_TEJARAT_TIMEOUT_140(0, 16207, "payment_error_tejarat_timeout_140"),
    PAYMENT_ERROR_TEJARAT_BANK_INTERNAL_ERROR_150(0, 16208, "payment_error_tejarat_bank_internal_error_150"),
    PAYMENT_ERROR_TEJARAT_INVALID_CVV2_160(0, 16209, "payment_error_tejarat_invalid_cvv2_160"),
    PAYMENT_ERROR_TEJARAT_NO_PERMIT_FROM_BANK_166(0, 16210, "payment_error_tejarat_no_permit_from_bank_166"),
    PAYMENT_ERROR_TEJARAT_INVALID_MONEY_167(0, 16211, "payment_error_tejarat_invalid_money_167"),
    PAYMENT_ERROR_TEJARAT_LIMIT_MONEY_200(0, 16212, "payment_error_tejarat_limit_money_200"),
    PAYMENT_ERROR_TEJARAT_LIMIT_MONEY_WORKDAY_201(0, 16213, "payment_error_tejarat_limit_money_workday_201"),
    PAYMENT_ERROR_TEJARAT_LIMIT_MONEY_WORKMONTH_202(0, 16214, "payment_error_tejarat_limit_money_workmonth_202"),
    PAYMENT_ERROR_TEJARAT_TOO_MANY_TIMES_TRANSACTION_203(0, 16215, "payment_error_tejarat_to_many_times_transaction_203"),
    PAYMENT_ERROR_TEJARAT_SYSTEM_ERROR_499(0, 16216, "payment_error_tejarat_system_error_499"),
    PAYMENT_ERROR_TEJARAT_VERIFICATION_TRANSACTION_500(0, 16217, "payment_error_tejarat_verification_transaction_500"),
    PAYMENT_ERROR_TEJARAT_AUTOMATIC_VERIFICATION_TRANSACTION_501(0, 16218, "payment_error_tejarat_automatic_verification_transaction_501"),
    PAYMENT_ERROR_TEJARAT_INAVLID_IP_ADDRESS_502(0, 16219, "payment_error_tejarat_invalid_ip_address_502"),
    PAYMENT_ERROR_TEJARAT_TESTSTATE_RECIVER_503(0, 16220, "payment_error_tejarat_teststate_reciver_503"),
    PAYMENT_ERROR_TEJARAT_PAYID_ALGORITHM_504(0, 16221, "payment_error_tejarat_payid_algorithm_504"),
    PAYMENT_ERROR_TEJARAT_VERIFICATION_TIMEOUT_505(0, 16222, "payment_error_tejarat_verification_timeout_505"),
    PAYMENT_ERROR_TEJARAT_NOT_FOUND_RECIVER_506(0, 16223, "payment_error_tejarat_not_found-reciver_506"),
    PAYMENT_ERROR_TEJARAT_INVALID_TOKEN_507(0, 16224, "payment_error_tejarat_invalid_token_507"),
    PAYMENT_ERROR_TEJARAT_NOT_FOUND_TOKEN_508(0, 16225, "payment_error_tejarat_not_found_token_508"),
    PAYMENT_ERROR_TEJARAT_INVALID_PAY_PARAMEETR_509(0, 16226, "payment_error_tejarat_invalid_pay_parametr_509"),
    PAYMENT_ERROR_TEJARAT_REPEAT_OR_NOT_MATCH_MONEY_510(0, 16227, "payment_error_tejarat_repeat_or_not-match_money_510"),
    PAYMENT_ERROR_TEJARAT_BLOCKED_ACCOUNT_511(0, 16228, "payment_error_tejarat_blocked_account_511"),
    PAYMENT_ERROR_TEJARAT_NOT_DEFINITION_ACCOUNT_512(0, 16229, "payment_error_tejarat_not_definition_account_512"),
    PAYMENT_ERROR_TEJARAT_REPEATED_TRANSATION_ID_513(0, 16230, "payment_error_tejarat_repeated_transaction_id_513"),
    PAYMENT_ERROR_TEJARAT_VERIFICATION_INVALID_CHARACTER_20_(0, 16231, "payment_error_tejarat_verification_invalid_character_20_"),
    PAYMENT_ERROR_TEJARAT_VERIFICATION_REPEATED_BACK_TRANSACTION_30_(0, 16232, "payment_error_tejarat_verification_repeated_back_transation_30_"),
    PAYMENT_ERROR_TEJARAT_VERIFICATION_INVALID_REQUEST_LENGHT_50_(0, 16233, "payment_error_tejarat_verification_invalid_request_lenght_50_"),
    PAYMENT_ERROR_TEJARAT_VERIFICATION_REWUEST_ERRROR_51_(0, 16234, "payment_error_tejarat_verification_request_error_51_"),
    PAYMENT_ERROR_TEJARAT_VERIFICATION_NOT_FOUND_TRANSATION_80_(0, 16235, "payment_error_tejarat_verification_not_found_transation_80_"),
    PAYMENT_ERROR_TEJARAT_VERIFICATION_INTERNAL_ERROR_81_(0, 16236, "payment_error_tejarat_verification_internal_error-81_"),
    PAYMENT_ERROR_TEJARAT_VERIFICATION_REPEATED_VERIFY_TRANSACTION_90_(0, 16237, "payment_error_tejarat_verification_repeated_verify_transation_90_"),
    PAYMENT_ERROR_TEJARAT_INVALID_CALLBACK_PARAMATR(0, 16238, "paymment_error_tejarat_invalid_callback_parametr"),
    PAYMENT_ERROR_TEJARAT_NO_MATCH_CALLBACK_PARAMATR(0, 16239, "paymment_error_tejarat_no_match_callback_parametr"),


    PAZH_SUBSCRIBER_TRANSACTION_CHECKED_BEFORE(0, 150020, "global_subscriber_transaction_failed"),
    ;


    private int status;
    private int code;
    private String message_key;

    Return_Status_Codes_Payment(int status, int code, String message_key) {
        this.status = status;
        this.code = code;
        this.message_key = message_key;
    }

    public static Return_Status_Codes_Payment get(int value) {

        if (value == 0) {
            return UNDEFINED;
        }

        Return_Status_Codes_Payment[] arr$ = values();
        for (Return_Status_Codes_Payment val : arr$) {
            if (val.code == value) {
                return val;
            }
        }

        return UNDEFINED;
    }

}
