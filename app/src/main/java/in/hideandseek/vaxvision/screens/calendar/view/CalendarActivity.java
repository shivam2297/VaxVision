package in.hideandseek.vaxvision.screens.calendar.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hideandseek.vaxvision.R;
import in.hideandseek.vaxvision.common.BaseActivity;
import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.libapi.calendar.model.Center;
import in.hideandseek.vaxvision.common.libapi.calendar.request.CalendarByDistrictApiRequest;
import in.hideandseek.vaxvision.screens.calendar.adapter.CentersAdapter;
import in.hideandseek.vaxvision.screens.calendar.manager.CalendarServiceManager;
import in.hideandseek.vaxvision.screens.calendar.presenter.CalenderPresenterImpl;
import in.hideandseek.vaxvision.screens.calendar.presenter.ICalendarPresenter;
import in.hideandseek.vaxvision.screens.calendar.viewmodel.CalenderViewModel;
import in.hideandseek.vaxvision.screens.calendar.viewmodel.CenterViewModel;
import in.hideandseek.vaxvision.screens.calendar.viewmodel.Filter;
import in.hideandseek.vaxvision.screens.sessions.view.SessionsActivity;

public class CalendarActivity extends BaseActivity implements ICalendarView {

    @BindView(R.id.filter_age_18)
    Chip mFilterAge18;

    @BindView(R.id.filter_age_45)
    Chip mFilterAge45;

    @BindView(R.id.filter_fee_free)
    Chip mFilterFeeFree;

    @BindView(R.id.filter_fee_paid)
    Chip mFilterFeePaid;

    @BindView(R.id.filter_vaccine_covax)
    Chip mFilterCovax;

    @BindView(R.id.filter_vaccine_covi)
    Chip mFilterCovi;

    @BindView(R.id.filter_vaccine_sputnik)
    Chip mFilterSputnik;

    @BindView(R.id.rv_calender)
    RecyclerView mCalenderRv;

    @BindView(R.id.tv_date_1)
    AppCompatTextView mDate1;

    @BindView(R.id.tv_date_2)
    AppCompatTextView mDate2;

    @BindView(R.id.tv_date_3)
    AppCompatTextView mDate3;

    @BindView(R.id.tv_date_4)
    AppCompatTextView mDate4;

    @BindView(R.id.tv_date_5)
    AppCompatTextView mDate5;

    @BindView(R.id.tv_date_6)
    AppCompatTextView mDate6;

    @BindView(R.id.tv_date_7)
    AppCompatTextView mDate7;

    private Filter mFilter = new Filter();
    private ArrayList<CenterViewModel> mCenters = new ArrayList<>();
    private CentersAdapter mCentersAdapter;

    private ICalendarPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);
        setupFilterListeners();
        mCentersAdapter = new CentersAdapter(mCenters, this);
        mCalenderRv.setLayoutManager(new LinearLayoutManager(this));
        mCalenderRv.setAdapter(mCentersAdapter);

        mPresenter = new CalenderPresenterImpl(new CalendarServiceManager(new CalendarByDistrictApiRequest()));
        mPresenter.onViewBeingCreated(this);

        int districtID = getIntent().getIntExtra(SessionsActivity.KEY_DISTRICT_ID, 0);
        String date = getIntent().getStringExtra(SessionsActivity.KEY_DATE);
        mPresenter.getCalendarFor(districtID, date);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onViewBeingCreated(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onViewBeingDestroyed();
        super.onDestroy();
    }

    @Override
    public void onApiFailure(ErrorResponse errorResponse) {
        super.onApiFailure(errorResponse);
        showSnackBar(errorResponse.getErrorMessage());
    }

    @Override
    public void onSuccessCalendar(CalenderViewModel viewModel) {
        SimpleDateFormat originalDateformat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE\ndd MMM");
        try {
            mDate1.setText(dateFormat.format(originalDateformat.parse(viewModel.dates.get(0))));
            mDate2.setText(dateFormat.format(originalDateformat.parse(viewModel.dates.get(1))));
            mDate3.setText(dateFormat.format(originalDateformat.parse(viewModel.dates.get(2))));
            mDate4.setText(dateFormat.format(originalDateformat.parse(viewModel.dates.get(3))));
            mDate5.setText(dateFormat.format(originalDateformat.parse(viewModel.dates.get(4))));
            mDate6.setText(dateFormat.format(originalDateformat.parse(viewModel.dates.get(5))));
            mDate7.setText(dateFormat.format(originalDateformat.parse(viewModel.dates.get(6))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mCenters.clear();
        mCenters.addAll(viewModel.centers);
        mCentersAdapter.notifyDataSetChanged();
    }

    private void setupFilterListeners() {
        mFilterAge18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mFilter.filterAge18 = b;
                mPresenter.getFilteredCalendar(mFilter);
            }
        });

        mFilterAge45.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mFilter.filterAge45 = b;
                mPresenter.getFilteredCalendar(mFilter);
            }
        });

        mFilterFeeFree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mFilter.filterFeeFree = b;
                mPresenter.getFilteredCalendar(mFilter);
            }
        });

        mFilterFeePaid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mFilter.filterFeePaid = b;
                mPresenter.getFilteredCalendar(mFilter);
            }
        });

        mFilterCovax.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b && !mFilter.filterVaccine.contains(Filter.VACCINE_FILTER_COVAXIN)) {
                    mFilter.filterVaccine.add(Filter.VACCINE_FILTER_COVAXIN);
                } else if (!b && mFilter.filterVaccine.contains(Filter.VACCINE_FILTER_COVAXIN)){
                    mFilter.filterVaccine.remove(Filter.VACCINE_FILTER_COVAXIN);
                }

                mPresenter.getFilteredCalendar(mFilter);
            }
        });

        mFilterCovi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b && !mFilter.filterVaccine.contains(Filter.VACCINE_FILTER_COVISHIELD)) {
                    mFilter.filterVaccine.add(Filter.VACCINE_FILTER_COVISHIELD);
                } else if (!b && mFilter.filterVaccine.contains(Filter.VACCINE_FILTER_COVISHIELD)){
                    mFilter.filterVaccine.remove(Filter.VACCINE_FILTER_COVISHIELD);
                }

                mPresenter.getFilteredCalendar(mFilter);
            }
        });

        mFilterSputnik.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b && !mFilter.filterVaccine.contains(Filter.VACCINE_FILTER_SPUTNIK)) {
                    mFilter.filterVaccine.add(Filter.VACCINE_FILTER_SPUTNIK);
                } else if (!b && mFilter.filterVaccine.contains(Filter.VACCINE_FILTER_SPUTNIK)){
                    mFilter.filterVaccine.remove(Filter.VACCINE_FILTER_SPUTNIK);
                }

                mPresenter.getFilteredCalendar(mFilter);
            }
        });
    }

    @OnClick(R.id.fl_register_cowin)
    void registerBtnTapped() {
        String url = "https://www.cowin.gov.in/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}