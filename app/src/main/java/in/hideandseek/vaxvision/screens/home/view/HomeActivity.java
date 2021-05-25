package in.hideandseek.vaxvision.screens.home.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hideandseek.vaxvision.R;
import in.hideandseek.vaxvision.common.BaseActivity;
import in.hideandseek.vaxvision.screens.otp.view.OtpActivity;
import in.hideandseek.vaxvision.screens.stateinfo.view.SelectStateActivity;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.btn_check_avail)
    FrameLayout btnCheckAvailability;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_check_avail)
    void checkAvailabilityBtnTapped() {
        launchActivity(new Intent(this, SelectStateActivity.class));
    }

    @OnClick(R.id.btn_user_val)
    void userAuthBtnTapped() {
        launchActivity(new Intent(this, OtpActivity.class));
    }

}