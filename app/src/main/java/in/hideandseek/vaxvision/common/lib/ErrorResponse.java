package in.hideandseek.vaxvision.common.lib;

public class ErrorResponse {
    private final int mErrorMessage;
    private String mErrorCode;
    private Throwable mErrorExp;

    public ErrorResponse(int mErrorMessage) {
        this.mErrorMessage = mErrorMessage;
    }

    public int getErrorMessage() {
        return mErrorMessage;
    }

    public String getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(String mErrorCode) {
        this.mErrorCode = mErrorCode;
    }

    public Throwable getErrorExp() {
        return mErrorExp;
    }

    public void setErrorExp(Throwable mErrorExp) {
        this.mErrorExp = mErrorExp;
    }
}
