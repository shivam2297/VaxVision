package in.hideandseek.vaxvision.common.libapi.otp.request;

import in.hideandseek.vaxvision.common.lib.ErrorMessageResolver;
import in.hideandseek.vaxvision.common.lib.ResponseCallback;
import in.hideandseek.vaxvision.common.lib.ResponseWrapper;
import in.hideandseek.vaxvision.common.lib.ServiceManager;
import in.hideandseek.vaxvision.common.libapi.otp.model.TokenModel;
import in.hideandseek.vaxvision.common.libapi.otp.model.TransIdModel;
import in.hideandseek.vaxvision.common.libapi.otp.service.IConfirmOtpService;
import in.hideandseek.vaxvision.common.libapi.otp.service.IGenerateOtpService;
import retrofit2.Call;

public class ConfirmOtpApiRequest {
    private Call<TokenModel> mConfirmeOtpCall;

    public void makeRequest(ResponseCallback<TokenModel> responseCallBack, ErrorMessageResolver errorMessageResolver,  String otp, String txnId) {
        IConfirmOtpService confirmOtpService = ServiceManager.getManager().createService(IConfirmOtpService.class);
        mConfirmeOtpCall = confirmOtpService.confirmOtp(otp, txnId);
        mConfirmeOtpCall.enqueue(new ResponseWrapper<>(responseCallBack, errorMessageResolver));
    }
}
