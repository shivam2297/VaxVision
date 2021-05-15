package in.hideandseek.vaxvision.screens.stateinfo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.datepicker.MaterialStyledDatePickerDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hideandseek.vaxvision.R;
import in.hideandseek.vaxvision.common.BaseActivity;
import in.hideandseek.vaxvision.common.Utils.MaterialSpinnerAdapter;
import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.libapi.statedistrict.model.District;
import in.hideandseek.vaxvision.common.libapi.statedistrict.model.State;
import in.hideandseek.vaxvision.common.libapi.statedistrict.request.GetDistrictsApiRequest;
import in.hideandseek.vaxvision.common.libapi.statedistrict.request.GetStatesApiRequest;
import in.hideandseek.vaxvision.screens.stateinfo.manager.StateDistrictServiceManager;
import in.hideandseek.vaxvision.screens.stateinfo.presenter.ISelectStatePresenter;
import in.hideandseek.vaxvision.screens.stateinfo.presenter.SelectStatePresenterImpl;

public class SelectStateActivity extends BaseActivity implements ISelectStateView {

    @BindView(R.id.dd_district)
    AutoCompleteTextView mddDistrict;

    @BindView(R.id.dd_state)
    AutoCompleteTextView mddState;

    @BindView(R.id.et_date)
    TextInputEditText mDateEditText;

    private ArrayList<State> mStates = new ArrayList<State>();
    private ArrayList<District> mDistricts = new ArrayList<District>();
    private ArrayList<String> mStateNames = new ArrayList<String>();
    private ArrayList<String> mDistrictNames = new ArrayList<String>();
    private MaterialSpinnerAdapter mStateDDAdapter;
    private MaterialSpinnerAdapter mDistrictDDAdapter;
    private State mSelectedState;
    private District mSelectedDistrict;

    private ISelectStatePresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_state);

        ButterKnife.bind(this);

        mPresenter = new SelectStatePresenterImpl(new StateDistrictServiceManager(new GetStatesApiRequest(), new GetDistrictsApiRequest()));
        mPresenter.onViewBeingCreated(this);
        mStateDDAdapter = new MaterialSpinnerAdapter(this, R.layout.support_simple_spinner_dropdown_item, mStateNames);
        mDistrictDDAdapter = new MaterialSpinnerAdapter(this, R.layout.support_simple_spinner_dropdown_item, mDistrictNames);

        mddState.setAdapter(mStateDDAdapter);
        mddDistrict.setAdapter(mDistrictDDAdapter);



        mddDistrict.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mDistricts.size() > 0 && i >= 0)
                    mSelectedDistrict = mDistricts.get(i);
            }
        });

        mddState.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mStates.size() > 0 && i >= 0) {
                    mSelectedState = mStates.get(i);
                    mPresenter.getDistricts(mSelectedState.getStateId());
                }
            }
        });

        mPresenter.getStates();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onViewBeingDestroyed();
        super.onDestroy();
    }

    @OnClick(R.id.et_date)
    void dateETTapped() {
        MaterialDatePicker picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Appointment Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();
        picker.show(getSupportFragmentManager(), "APT_DATE");

        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                updateDateLabel(picker.getHeaderText());
            }
        });

    }

    private void updateDateLabel(String date) {
        mDateEditText.setText(date);
    }

    @Override
    public void onApiFailure(ErrorResponse errorResponse) {
        showSnackBar(errorResponse.getErrorMessage());
    }

    @Override
    public void onSuccessStates(List<State> states) {
        mStates.clear();
        mStates.addAll(states);
        mStateNames.clear();
        for (State state :
                mStates) {
            mStateNames.add(state.getStateName());
        }
    }

    @Override
    public void onSuccessDistricts(List<District> districts) {
        mDistricts.clear();
        mDistricts.addAll(districts);
        mDistrictNames.clear();
        for (District district :
                mDistricts) {
            mDistrictNames.add(district.getDistrictName());
        }
    }
}