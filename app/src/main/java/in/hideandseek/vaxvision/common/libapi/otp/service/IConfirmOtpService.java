package in.hideandseek.vaxvision.common.libapi.otp.service;

import in.hideandseek.vaxvision.common.lib.ApiConstants;
import in.hideandseek.vaxvision.common.libapi.otp.model.TokenModel;
import in.hideandseek.vaxvision.common.libapi.otp.model.TransIdModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IConfirmOtpService {

    @POST(ApiConstants.CONFIRM_OTP)
    @FormUrlEncoded
    Call<TokenModel> confirmOtp(@Field("otp") String otpData,
                                @Field("txnId") String txnId);
}
