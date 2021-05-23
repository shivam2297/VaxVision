package in.hideandseek.vaxvision.screens.splash.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import in.hideandseek.vaxvision.R;
import in.hideandseek.vaxvision.common.BaseActivity;
import in.hideandseek.vaxvision.screens.home.view.HomeActivity;
import in.hideandseek.vaxvision.screens.splash.presenter.SplashPresenterImpl;

public class SplashActivity extends BaseActivity implements ISplashView {

    private SplashPresenterImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mPresenter = new SplashPresenterImpl();
        mPresenter.onViewBeingCreated(this);
        mPresenter.startTimer();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onViewBeingDestroyed();
        super.onDestroy();
    }

    @Override
    public void onSplashTimeOut() {
        launchActivity(this, HomeActivity.class);
        finish();
    }
}