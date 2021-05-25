package in.hideandseek.vaxvision.screens.splash.presenter;

import android.os.Handler;

import in.hideandseek.vaxvision.common.IBasePresenter;
import in.hideandseek.vaxvision.screens.splash.view.ISplashView;

public class SplashPresenterImpl implements IBasePresenter<ISplashView> {
    private ISplashView mView;
    private static int SPLASH_TIMEOUT = 3000;

    public void startTimer() {
//        mView.showProgress();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mView != null){
//                    mView.hideProgress();
                    mView.onSplashTimeOut();
                }
            }
        }, SPLASH_TIMEOUT);
    }
    @Override
    public void onViewBeingCreated(ISplashView view) {
        mView = view;
    }

    @Override
    public void onViewBeingDestroyed() {
        if (mView != null)
            mView = null;
    }
}
