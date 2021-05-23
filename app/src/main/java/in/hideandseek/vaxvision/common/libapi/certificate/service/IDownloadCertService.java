package in.hideandseek.vaxvision.common.libapi.certificate.service;

import in.hideandseek.vaxvision.common.lib.ApiConstants;
import in.hideandseek.vaxvision.common.libapi.otp.model.TokenModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface IDownloadCertService {

    @GET(ApiConstants.DOWNLOAD_CERTIFICATE)
    Call<ResponseBody> download(@Header("authorization") String token,
                                @Query("beneficiary_reference_id") String refId);
}
