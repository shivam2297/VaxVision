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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hideandseek.vaxvision.R;
import in.hideandseek.vaxvision.common.Utils.MaterialSpinnerAdapter;

public class SelectStateActivity extends AppCompatActivity {

    @BindView(R.id.dd_district)
    AutoCompleteTextView mddDistrict;

    @BindView(R.id.dd_state)
    AutoCompleteTextView mddState;

    @BindView(R.id.et_date)
    TextInputEditText mDateEditText;

    private ArrayList<String> mStateNames = new ArrayList<String>();
    private ArrayList<String> mDistrictNames = new ArrayList<String>();
    private MaterialSpinnerAdapter mStateDDAdapter;
    private MaterialSpinnerAdapter mDistrictDDAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_state);

        ButterKnife.bind(this);

        mStateDDAdapter = new MaterialSpinnerAdapter(this, R.layout.support_simple_spinner_dropdown_item, mStateNames);
        mDistrictDDAdapter = new MaterialSpinnerAdapter(this, R.layout.support_simple_spinner_dropdown_item, mDistrictNames);

        mddState.setAdapter(mStateDDAdapter);
        mddDistrict.setAdapter(mDistrictDDAdapter);

        mddState.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        mddDistrict.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
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
}