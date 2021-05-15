package in.hideandseek.vaxvision.common.libapi.statedistrict.service;

import in.hideandseek.vaxvision.common.lib.ApiConstants;
import in.hideandseek.vaxvision.common.lib.ResponseModel;
import in.hideandseek.vaxvision.common.libapi.statedistrict.model.DistrictList;
import in.hideandseek.vaxvision.common.libapi.statedistrict.model.StateList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IGetDistrictsService {

    @GET(ApiConstants.GET_DISTRICT)
    Call<DistrictList> getDistricts(@Path("state_id") int stateID);
}
