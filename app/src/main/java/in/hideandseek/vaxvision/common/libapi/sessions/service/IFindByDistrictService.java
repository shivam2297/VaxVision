package in.hideandseek.vaxvision.common.libapi.sessions.service;

import in.hideandseek.vaxvision.common.lib.ApiConstants;
import in.hideandseek.vaxvision.common.libapi.sessions.model.CurrentSessionList;
import in.hideandseek.vaxvision.common.libapi.statedistrict.model.StateList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IFindByDistrictService {

    @GET(ApiConstants.FIND_BY_DISTRICT)
    Call<CurrentSessionList> getCurrentSessions(@Query("district_id ") int districtId,
                                                @Query("date") String date);
}
