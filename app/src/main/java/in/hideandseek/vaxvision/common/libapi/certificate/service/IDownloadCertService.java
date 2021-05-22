package in.hideandseek.vaxvision.common.libapi.certificate.service;

import in.hideandseek.vaxvision.common.lib.ApiConstants;
import in.hideandseek.vaxvision.common.libapi.otp.model.TokenModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IDownloadCertService {

    @GET(ApiConstants.DOWNLOAD_CERTIFICATE)
    Call<String> download(@Header("authorization") String token,
                          @Query("beneficiary_reference_id") String refId);
}
