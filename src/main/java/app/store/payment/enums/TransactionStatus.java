package app.store.payment.enums;

import app.store.service.util.General;

public enum TransactionStatus {

    UNDEFINED("UNDEFINED"),
    NOT_CHECKED("NOT_CHECKED"),
    INVALID_AUTHORITY("INVALID_AUTHORITY"),
    PROCESSED_ERROR("PROCESSED_ERROR"),
    PROCESSED_ERROR_REQUEST_CALL_FAILED("PROCESSED_ERROR_REQUEST_CALL_FAILED"),
    PROCESSED_DONE("PROCESSED_DONE"),
    CHARGE_ERROR_INVALID_PARAMETER("charge_error_invalid_parameter"),
    CHARGE_ERROR_EXCEPTION("charge_error_exception"),
    CALLBACK_VALIDITY_OK("callback_validity_ok"),
    CALLBACK_VALIDITY_ERROR("callback_validity_error"),
    CALLBACK_VALIDITY_EXCEPTION("callback_validity_exception"),
    CALLBACK_VALIDITY_EXCEPTION_BANK("callback_validity_exception_bank"),
    CHECKING("checking"),
    NOTCONFIRM("notconfirm"),
    //READY("ready"),
    CALLBACK_ERROR_USER("callback_error_user"),
    NO_MATCH_CALLBACK_PARAMETER("no-no_match_callback_parameter"),
    ;


    private String value;

    TransactionStatus(String value) {
        this.value = value;
    }

    public static TransactionStatus get(String value) {
        if (General.isEmpty(value)) {
            return UNDEFINED;
        }

        TransactionStatus[] arr$ = values();
        for (TransactionStatus val : arr$) {
            if (val.value.equalsIgnoreCase(value)) {
                return val;
            }
        }
        return UNDEFINED;
    }

    public String getValue() {
        return value;
    }
}