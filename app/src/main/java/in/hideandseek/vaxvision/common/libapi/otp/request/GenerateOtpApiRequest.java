package in.hideandseek.vaxvision.common.libapi.otp.request;

import in.hideandseek.vaxvision.common.lib.ErrorMessageResolver;
import in.hideandseek.vaxvision.common.lib.ResponseCallback;
import in.hideandseek.vaxvision.common.lib.ResponseWrapper;
import in.hideandseek.vaxvision.common.lib.ServiceManager;
import in.hideandseek.vaxvision.common.libapi.otp.model.GenerateOtpReqModel;
import in.hideandseek.vaxvision.common.libapi.otp.model.TransIdModel;
import in.hideandseek.vaxvision.common.libapi.otp.service.IGenerateOtpService;
import in.hideandseek.vaxvision.common.libapi.sessions.model.CurrentSessionList;
import in.hideandseek.vaxvision.common.libapi.sessions.service.IFindByDistrictService;
import retrofit2.Call;

public class GenerateOtpApiRequest {
    private Call<TransIdModel> mGenearateOtpCall;

    public void makeRequest(ResponseCallback<TransIdModel> responseCallBack, ErrorMessageResolver errorMessageResolver,  String mobile) {
        IGenerateOtpService generateOtpService = ServiceManager.getManager().createService(IGenerateOtpService.class);
        mGenearateOtpCall = generateOtpService.generateOtp(new GenerateOtpReqModel(mobile));
        mGenearateOtpCall.enqueue(new ResponseWrapper<>(responseCallBack, errorMessageResolver));
    }
}
