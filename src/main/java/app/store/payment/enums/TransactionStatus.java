package app.store.payment.enums;

import app.store.service.util.General;

public enum TransactionStatus {

    UNDEFINED("UNDEFINED"),
    NOT_CHECKED("NOT_CHECKED"),
    INVALID_AUTHORITY("INVALID_AUTHORITY"),
    PROCESSED_ERROR("PROCESSED_ERROR"),
    PROCESSED_ERROR_REQUEST_CALL_FAILED("PROCESSED_ERROR_REQUEST_CALL_FAILED"),
    PROCESSED_DONE("PROCESSED_DONE"),
    CHARGE_ERROR_INVALID_PARAMETER("CHARGE_ERROR_INVALID_PARAMETER"),
    CHARGE_ERROR_EXCEPTION("CHARGE_ERROR_EXCEPTION"),
    CALLBACK_VALIDITY_OK("CALLBACK_VALIDITY_OK"),
    CALLBACK_VALIDITY_ERROR("CALLBACK_VALIDITY_ERROR"),
    CALLBACK_VALIDITY_EXCEPTION("CALLBACK_VALIDITY_EXCEPTION"),
    CALLBACK_VALIDITY_EXCEPTION_BANK("CALLBACK_VALIDITY_EXCEPTION_BANK"),
    CHECKING("checking"),
    NOTCONFIRM("notconfirm"),
    //READY("ready"),
    CALLBACK_ERROR_USER("CALLBACK_ERROR_USER"),
    NO_MATCH_CALLBACK_PARAMETER("NO_MATCH_CALLBACK_PARAMETER"),
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