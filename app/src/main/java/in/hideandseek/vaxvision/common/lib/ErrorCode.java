package in.hideandseek.vaxvision.common.lib;

public enum ErrorCode {

    DEFAULT("0"),
    STATUS_OK("200"),
    INVALID_CREDENTIALS("3001"),
    INVALID_TOKEN("401"),
    INVALID_ARGS("402"),
    SERVER_ERROR("500"),
    DB_ERROR("1001"),
    COLUMN_NOT_FOUND("1002"),
    TABLE_NOT_FOUND("1003"),
    DUPLICATE_COLUMN_DATA("1004"),
    DATA_INSERT_FAILED("1005"),
    DATA_UPDATE_FAILED("1006"),
    DATA_DELETE_FAILED("1007"),
    DUPLICATE_BNAME("1008"),
    DUPLICATE_BEMAIL("1009"),
    DUPLICATE_REGISTRATIONREF("1010"),
    DUPLICATE_USER("2001"),
    USER_EXISTS("1012"),
    USER_NOT_FOUND("1013"),
    BUSINESS_NOT_FOUND("1014"),
    PLAN_NOT_FOUND("1015"),
    ACCOUNTTYPES_NOT_FOUND("1016"),
    PARAM_ERROR("6000"),
    UNKNOWN_ERROR("9999"),
    EXCEPTION_ERROR("1")
    ;


    private String errorCodeValue;

    ErrorCode(String errorCodeValue) {
        this.errorCodeValue = errorCodeValue;
    }

    public String getErrorCodeValue() {
        return errorCodeValue;
    }
}
