package in.hideandseek.vaxvision.screens.otp.manager;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.lib.ResponseCallback;
import in.hideandseek.vaxvision.common.libapi.otp.model.TokenModel;
import in.hideandseek.vaxvision.screens.otp.presenter.IConfirmOtpResReceiver;

public class ConfirmOtpResProcessor implements ResponseCallback<TokenModel> {

    private IConfirmOtpResReceiver resReceiver;

    public ConfirmOtpResProcessor(IConfirmOtpResReceiver resReceiver) {
        this.resReceiver = resReceiver;
    }

    @Override
    public void onSuccess(TokenModel data) {
        resReceiver.onOtpConfirmed(data.getToken());
    }

    @Override
    public void onFailure(ErrorResponse error) {
        resReceiver.onFailure(error);
    }
}
