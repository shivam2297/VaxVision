package in.hideandseek.vaxvision.screens.calendar.presenter;

import in.hideandseek.vaxvision.common.IBasePresenter;
import in.hideandseek.vaxvision.screens.calendar.view.ICalendarView;
import in.hideandseek.vaxvision.screens.calendar.viewmodel.Filter;

public interface ICalendarPresenter extends IBasePresenter<ICalendarView> {

    void getCalendarFor(int districtId, String date);
    void getFilteredCalendar(Filter filter);
}
