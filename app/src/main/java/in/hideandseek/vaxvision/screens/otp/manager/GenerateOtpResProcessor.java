package in.hideandseek.vaxvision.screens.otp.manager;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.lib.ResponseCallback;
import in.hideandseek.vaxvision.common.libapi.otp.model.TransIdModel;
import in.hideandseek.vaxvision.screens.otp.presenter.IGenerateOtpResReceiver;

public class GenerateOtpResProcessor implements ResponseCallback<TransIdModel> {

    private IGenerateOtpResReceiver resReceiver;

    public GenerateOtpResProcessor(IGenerateOtpResReceiver resReceiver) {
        this.resReceiver = resReceiver;
    }

    @Override
    public void onSuccess(TransIdModel data) {
        resReceiver.onSuccessGenerateOtp(data.getTxnId());
    }

    @Override
    public void onFailure(ErrorResponse error) {
        resReceiver.onFailure(error);
    }
}
