package in.hideandseek.vaxvision.screens.calendar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import butterknife.ButterKnife;
import in.hideandseek.vaxvision.R;
import in.hideandseek.vaxvision.common.BaseActivity;
import in.hideandseek.vaxvision.common.lib.ErrorResponse;

public class CalendarActivity extends BaseActivity implements ICalendarView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);
    }

    @Override
    public void onApiFailure(ErrorResponse errorResponse) {
        super.onApiFailure(errorResponse);
        showSnackBar(errorResponse.getErrorMessage());
    }
}