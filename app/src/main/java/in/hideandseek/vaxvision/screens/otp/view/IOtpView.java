package in.hideandseek.vaxvision.screens.otp.view;

import in.hideandseek.vaxvision.common.IBaseView;
import in.hideandseek.vaxvision.common.lib.ErrorResponse;

public interface IOtpView extends IBaseView {

    void onSuccessGenerateOtp(String txnId);
    void onOtpConfirmed(String token);
    void onFailure(ErrorResponse errorResponse);
}
