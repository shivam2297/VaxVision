package in.hideandseek.vaxvision.common.libapi.otp.service;

import in.hideandseek.vaxvision.common.lib.ApiConstants;
import in.hideandseek.vaxvision.common.libapi.otp.model.GenerateOtpReqModel;
import in.hideandseek.vaxvision.common.libapi.otp.model.TransIdModel;
import in.hideandseek.vaxvision.common.libapi.sessions.model.CurrentSessionList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IGenerateOtpService {

    @Headers("Content-Type: application/json")
    @POST(ApiConstants.GENERATE_OTP)
    Call<TransIdModel> generateOtp(@Body GenerateOtpReqModel model);
}
