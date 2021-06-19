package in.hideandseek.vaxvision.screens.calendar.presenter;

import java.util.List;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.libapi.calendar.model.Center;
import in.hideandseek.vaxvision.screens.calendar.manager.CalendarServiceManager;
import in.hideandseek.vaxvision.screens.calendar.view.ICalendarView;

public class CalenderPresenterImpl implements ICalendarPresenter, ICalendarByDistrictResReceiver {
    private ICalendarView mView;
    private CalendarServiceManager mManager;
    private List<Center> mOriginalCenters;

    public CalenderPresenterImpl(CalendarServiceManager mManager) {
        this.mManager = mManager;
    }

    @Override
    public void onViewBeingCreated(ICalendarView view) {
        mView = view;
    }

    @Override
    public void onViewBeingDestroyed() {
        if (mView != null) {
            mView.hideProgress();
            mView = null;
        }
    }

    @Override
    public void onSuccessSessions(List<Center> centers) {
        mOriginalCenters = centers;
    }

    @Override
    public void onFailure(ErrorResponse errorResponse) {
        if (mView != null) {
            mView.hideProgress();
            mView.onApiFailure(errorResponse);
        }
    }

    @Override
    public void getCalendarFor(int districtId, String date) {
        if (mView != null) {
            mView.showProgress();
            mManager.calendarByDistrict(this, districtId, date);
        }
    }
}
