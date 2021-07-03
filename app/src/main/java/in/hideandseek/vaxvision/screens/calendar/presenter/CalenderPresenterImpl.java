package in.hideandseek.vaxvision.screens.calendar.presenter;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.libapi.calendar.model.Center;
import in.hideandseek.vaxvision.common.libapi.calendar.model.Session;
import in.hideandseek.vaxvision.screens.calendar.manager.CalendarServiceManager;
import in.hideandseek.vaxvision.screens.calendar.view.ICalendarView;
import in.hideandseek.vaxvision.screens.calendar.viewmodel.CalenderViewModel;
import in.hideandseek.vaxvision.screens.calendar.viewmodel.CenterViewModel;
import in.hideandseek.vaxvision.screens.calendar.viewmodel.Filter;

public class CalenderPresenterImpl implements ICalendarPresenter, ICalendarByDistrictResReceiver {
    private ICalendarView mView;
    private CalendarServiceManager mManager;
    private List<Center> mOriginalCenters;
    private ArrayList<String> mDates;

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
        if (mView != null) {
            CalenderViewModel viewModel = calendarViewModel(new Filter());
            mView.hideProgress();
            mView.onSuccessCalendar(viewModel);
        }
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
            mDates = datesForSevenDaysFrom(date);
            mManager.calendarByDistrict(this, districtId, date);
        }
    }

    @Override
    public void getFilteredCalendar(Filter filter) {
        if (mView != null) {
            CalenderViewModel viewModel = calendarViewModel(filter);
            mView.hideProgress();
            mView.onSuccessCalendar(viewModel);
        }
    }


    private ArrayList<String> datesForSevenDaysFrom(String date) {
        // Past days in the future
        int past = 7;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            calendar.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < past; i++) {
            if (i == 0) {
                calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 0);
            } else if (i > 0) {
                calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
            }
            Date today = calendar.getTime();
            String result = format.format(today);
            arrayList.add(result);

        }
        return arrayList;
    }

    private CalenderViewModel calendarViewModel(Filter filter) {
        CalenderViewModel viewModel = new CalenderViewModel();
        viewModel.filter = filter;
        viewModel.dates = mDates;

        if (mDates != null && mDates.size() > 0 && mOriginalCenters != null && mOriginalCenters.size() > 0) {
            for (Center center :
                    mOriginalCenters) {
                // check fee type filter
                if ((filter.filterFeePaid && !filter.filterFeeFree) || (filter.filterFeeFree && !filter.filterFeePaid)) {
                    if ((filter.filterFeeFree && !Filter.FEE_FILTER_FREE.equals(center.getFeeType())) || (filter.filterFeePaid && !Filter.FEE_FILTER_PAID.equals(center.getFeeType()))) {
                        continue;
                    }
                }
                CenterViewModel centerViewModel = new CenterViewModel(
                        center.getName(),
                        center.getAddress(),
                        center.getStateName(),
                        center.getDistrictName(),
                        center.getBlockName(),
                        center.getPincode(),
                        center.getLat(),
                        center.getLong(),
                        center.getFeeType(),
                        center.getVaccineFees());

                // iterate over original centers to filter and generate the viewmodel
                for (int i = 0; i < mDates.size(); i++) {
                    String dateString = mDates.get(i);
                    Date date;
                    try {
                        date = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    // itereate complete centers array for each date in week
                    // session list for current date
                    ArrayList<Session> sessions = (ArrayList<Session>) centerViewModel.sessions.get(i);
                    for (Session originalSession : center.getSessions()) {
                        // check for filters and dates
                        if (!dateString.equals(originalSession.getDate()))
                            continue;

                        if ((filter.filterAge45 && !filter.filterAge18) || (filter.filterAge18 && !filter.filterAge45)) {
                            if ((filter.filterAge18 && Filter.AGE_FILTER_18 != originalSession.getMinAgeLimit()) ||
                                    (filter.filterAge45 && Filter.AGE_FILTER_45 != originalSession.getMinAgeLimit())) {
                                continue;
                            }
                        }

                        if (filter.filterVaccine.size() > 0 && !filter.filterVaccine.contains(originalSession.getVaccine())) {
                            continue;
                        }

                        // if filters and dates match add session to list
                        sessions.add(originalSession);
                    }
                }

                // add centerviewmodel to the list
                viewModel.centers.add(centerViewModel);
            }
        }

        return viewModel;
    }
}
