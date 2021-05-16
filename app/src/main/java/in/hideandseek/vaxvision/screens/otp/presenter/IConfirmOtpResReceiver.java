package in.hideandseek.vaxvision.screens.otp.presenter;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;

public interface IConfirmOtpResReceiver {

    void onOtpConfirmed(String token);
    void onFailure(ErrorResponse errorResponse);
}
