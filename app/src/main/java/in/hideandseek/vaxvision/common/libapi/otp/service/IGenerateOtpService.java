package in.hideandseek.vaxvision.common.libapi.otp.service;

import in.hideandseek.vaxvision.common.lib.ApiConstants;
import in.hideandseek.vaxvision.common.libapi.otp.model.TransIdModel;
import in.hideandseek.vaxvision.common.libapi.sessions.model.CurrentSessionList;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IGenerateOtpService {

    @POST(ApiConstants.GENERATE_OTP)
    @FormUrlEncoded
    Call<TransIdModel> generateOtp(@Field("mobile") String mobile);
}
