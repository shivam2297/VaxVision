package in.hideandseek.vaxvision.screens.otp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import butterknife.ButterKnife;
import in.hideandseek.vaxvision.R;
import in.hideandseek.vaxvision.common.BaseActivity;

public class OtpActivity extends BaseActivity implements IOtpView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        ButterKnife.bind(this);
    }
}