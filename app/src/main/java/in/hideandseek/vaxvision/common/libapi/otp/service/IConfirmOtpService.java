package in.hideandseek.vaxvision.common.libapi.otp.service;

import in.hideandseek.vaxvision.common.lib.ApiConstants;
import in.hideandseek.vaxvision.common.libapi.otp.model.ConfirmOTPReqModel;
import in.hideandseek.vaxvision.common.libapi.otp.model.TokenModel;
import in.hideandseek.vaxvision.common.libapi.otp.model.TransIdModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IConfirmOtpService {

    @Headers("Content-Type: application/json")
    @POST(ApiConstants.CONFIRM_OTP)
    Call<TokenModel> confirmOtp(@Body ConfirmOTPReqModel model);
}
