package in.hideandseek.vaxvision.screens.calendar.presenter;

import java.util.List;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.libapi.calendar.model.Center;
import in.hideandseek.vaxvision.common.libapi.sessions.model.Session;

public interface ICalendarByDistrictResReceiver {

    void onSuccessSessions(List<Center> centers);
    void onFailure(ErrorResponse errorResponse);
}
