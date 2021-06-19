package in.hideandseek.vaxvision.screens.calendar.manager;

import in.hideandseek.vaxvision.common.lib.ErrorMessageResolver;
import in.hideandseek.vaxvision.common.libapi.calendar.request.CalendarByDistrictApiRequest;
import in.hideandseek.vaxvision.common.libapi.sessions.request.FindByDistrictApiRequest;
import in.hideandseek.vaxvision.screens.calendar.presenter.ICalendarByDistrictResReceiver;
import in.hideandseek.vaxvision.screens.sessions.manager.FindByDistrictResponseProcessor;
import in.hideandseek.vaxvision.screens.sessions.presenter.IFindByDistrictResReceiver;

public class CalendarServiceManager {

    private CalendarByDistrictApiRequest apiRequest;

    public CalendarServiceManager(CalendarByDistrictApiRequest apiRequest) {
        this.apiRequest = apiRequest;
    }


    public void calendarByDistrict(ICalendarByDistrictResReceiver resReceiver, int districtId, String date) {
        apiRequest.makeRequest(new CalendarByDistrictResponseProcessor(resReceiver), ErrorMessageResolver.getStandardErrorResolver(), districtId, date);
    }
}
