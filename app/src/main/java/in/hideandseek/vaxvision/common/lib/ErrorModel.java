package in.hideandseek.vaxvision.common.lib;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorModel {
    @SerializedName("errorCode")
    @Expose
    private String errorCode;
    @SerializedName("error")
    @Expose
    private String error;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
