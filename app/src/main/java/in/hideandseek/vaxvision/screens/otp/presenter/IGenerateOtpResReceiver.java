package in.hideandseek.vaxvision.screens.otp.presenter;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;

public interface IGenerateOtpResReceiver {

    void onSuccessGenerateOtp(String txnId);
    void onFailure(ErrorResponse errorResponse);
}
