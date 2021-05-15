package in.hideandseek.vaxvision.common.lib;

import in.hideandseek.vaxvision.R;
import java.util.HashMap;
import java.util.Map;

public class ErrorMessageResolver {

    private Map<String, Integer> mErrorMapper;

    public ErrorMessageResolver() {
        mErrorMapper = new HashMap<>();
    }

    public static ErrorMessageResolver getStandardErrorResolver() {
        ErrorMessageResolver standardResolver = new ErrorMessageResolver();
        standardResolver.translate(ErrorCode.INVALID_TOKEN.getErrorCodeValue(), R.string.INVALID_TOKEN);
        standardResolver.translate(ErrorCode.INVALID_ARGS.getErrorCodeValue(), R.string.INVALID_ARGS);
        standardResolver.translate(ErrorCode.INVALID_CREDENTIALS.getErrorCodeValue(), R.string.INVALID_CREDENTIALS);
        standardResolver.translate(ErrorCode.DB_ERROR.getErrorCodeValue(),R.string.DB_ERROR);
        standardResolver.translate(ErrorCode.COLUMN_NOT_FOUND.getErrorCodeValue(),R.string.COLUMN_NOT_FOUND);
        standardResolver.translate(ErrorCode.TABLE_NOT_FOUND.getErrorCodeValue(),R.string.TABLE_NOT_FOUND);
        standardResolver.translate(ErrorCode.DUPLICATE_COLUMN_DATA.getErrorCodeValue(),R.string.DUPLICATE_COLUMN_DATA);
        standardResolver.translate(ErrorCode.DATA_INSERT_FAILED.getErrorCodeValue(),R.string.DATA_INSERT_FAILED);
        standardResolver.translate(ErrorCode.DATA_UPDATE_FAILED.getErrorCodeValue(), R.string.DATA_UPDATE_FAILED);
        standardResolver.translate(ErrorCode.DATA_DELETE_FAILED.getErrorCodeValue(),R.string.DATA_DELETE_FAILED);
        standardResolver.translate(ErrorCode.DUPLICATE_BNAME.getErrorCodeValue(),R.string.DUPLICATE_BNAME);
        standardResolver.translate(ErrorCode.DUPLICATE_BEMAIL.getErrorCodeValue(),R.string.DUPLICATE_BEMAIL);
        standardResolver.translate(ErrorCode.DUPLICATE_REGISTRATIONREF.getErrorCodeValue(),R.string.DUPLICATE_REGISTRATIONREF);
        standardResolver.translate(ErrorCode.DUPLICATE_USER.getErrorCodeValue(), R.string.DUPLICATE_USER);
        standardResolver.translate(ErrorCode.USER_EXISTS.getErrorCodeValue(), R.string.USER_EXISTS);
        standardResolver.translate(ErrorCode.BUSINESS_NOT_FOUND.getErrorCodeValue(), R.string.BUSINESS_NOT_FOUND);
        standardResolver.translate(ErrorCode.PLAN_NOT_FOUND.getErrorCodeValue(), R.string.PLAN_NOT_FOUND);
        standardResolver.translate(ErrorCode.ACCOUNTTYPES_NOT_FOUND.getErrorCodeValue(), R.string.ACCOUNTTYPES_NOT_FOUND);
        standardResolver.translate(ErrorCode.EXCEPTION_ERROR.getErrorCodeValue(), R.string.some_error_occured);
        standardResolver.translate(ErrorCode.PARAM_ERROR.getErrorCodeValue(), R.string.some_error_occured);
        standardResolver.translate(ErrorCode.UNKNOWN_ERROR.getErrorCodeValue(), R.string.some_error_occured);
        standardResolver.translate(ErrorCode.USER_NOT_FOUND.getErrorCodeValue(), R.string.USER_NOT_FOUND);
        standardResolver.translate(ErrorCode.SERVER_ERROR.getErrorCodeValue(), R.string.SERVER_ERROR);

        return standardResolver;
    }

    public void translate(String code, int message) {
        mErrorMapper.put(code, message);
    }

    public boolean isResolvable(String code) {
        return mErrorMapper.containsKey(code);
    }

    public int resolve(String code) {
        return isResolvable(code) ? mErrorMapper.get(code) : R.string.some_error_occured;
    }
}
