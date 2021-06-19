package in.hideandseek.vaxvision.common.libapi.calendar.service;

import in.hideandseek.vaxvision.common.lib.ApiConstants;
import in.hideandseek.vaxvision.common.libapi.calendar.model.Centers;
import in.hideandseek.vaxvision.common.libapi.sessions.model.CurrentSessionList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ICalendarByDistrictService {

    @GET(ApiConstants.CALENDAR_BY_DISTRICT)
    Call<Centers> getCalendarFor(@Query("district_id") int districtId,
                                     @Query("date") String date);
}
