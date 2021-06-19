package in.hideandseek.vaxvision.screens.calendar.manager;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.lib.ResponseCallback;
import in.hideandseek.vaxvision.common.libapi.calendar.model.Centers;
import in.hideandseek.vaxvision.screens.calendar.presenter.ICalendarByDistrictResReceiver;

public class CalendarByDistrictResponseProcessor implements ResponseCallback<Centers> {
    private ICalendarByDistrictResReceiver resReceiver;

    public CalendarByDistrictResponseProcessor(ICalendarByDistrictResReceiver resReceiver) {
        this.resReceiver = resReceiver;
    }

    @Override
    public void onSuccess(Centers data) {
        resReceiver.onSuccessSessions(data.getCenters());
    }

    @Override
    public void onFailure(ErrorResponse error) {
        resReceiver.onFailure(error);
    }
}
