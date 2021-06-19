package in.hideandseek.vaxvision.common.libapi.calendar.request;

import in.hideandseek.vaxvision.common.lib.ErrorMessageResolver;
import in.hideandseek.vaxvision.common.lib.ResponseCallback;
import in.hideandseek.vaxvision.common.lib.ResponseWrapper;
import in.hideandseek.vaxvision.common.lib.ServiceManager;
import in.hideandseek.vaxvision.common.libapi.calendar.model.Centers;
import in.hideandseek.vaxvision.common.libapi.calendar.service.ICalendarByDistrictService;
import in.hideandseek.vaxvision.common.libapi.sessions.model.CurrentSessionList;
import in.hideandseek.vaxvision.common.libapi.sessions.service.IFindByDistrictService;
import retrofit2.Call;

public class CalendarByDistrictApiRequest {
    private Call<Centers> mCall;

    public void makeRequest(ResponseCallback<Centers> responseCallBack, ErrorMessageResolver errorMessageResolver, int districtID, String date) {
        ICalendarByDistrictService calendarByDistrictService = ServiceManager.getManager().createService(ICalendarByDistrictService.class);
        mCall = calendarByDistrictService.getCalendarFor(districtID, date);
        mCall.enqueue(new ResponseWrapper<>(responseCallBack, errorMessageResolver));
    }
}
