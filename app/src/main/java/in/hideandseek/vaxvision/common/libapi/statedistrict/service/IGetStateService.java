package in.hideandseek.vaxvision.common.libapi.statedistrict.service;

import in.hideandseek.vaxvision.common.lib.ApiConstants;
import in.hideandseek.vaxvision.common.lib.ResponseModel;
import in.hideandseek.vaxvision.common.libapi.statedistrict.model.StateList;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IGetStateService {

    @GET(ApiConstants.GET_STATES)
    Call<ResponseModel<StateList>> getStates();
}
