package in.hideandseek.vaxvision.common;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import in.hideandseek.vaxvision.common.custom_views.BKLoader;
import in.hideandseek.vaxvision.common.eventbus.GlobalBus;
import in.hideandseek.vaxvision.common.internet_check_reciever.InternetCheckReceiver;
import in.hideandseek.vaxvision.common.lib.ErrorResponse;

import in.hideandseek.vaxvision.R;

public class BaseActivity extends AppCompatActivity implements IBaseView {
    private InternetCheckReceiver mInternetReceiver;
    public static boolean sIsNoInternetSnacShow;
    private Snackbar mSnacbar;
    private boolean mWasOffline;
    public static boolean sNoInternetShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInternetReceiver = new InternetCheckReceiver();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        GlobalBus.getsBus().register(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(mInternetReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        GlobalBus.getsBus().unregister(this);
        unregisterReceiver(mInternetReceiver);
    }

    @Override
    public void showProgress() {
        BKLoader.getInstance().show(this);
    }

    @Override
    public void hideProgress() {
        BKLoader.getInstance().dismiss();
    }

    @Override
    public void showSnackBar(int message) {
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        Snackbar snackbar = Snackbar.make(viewGroup, message, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        TextView textView = (TextView) snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setMaxLines(5);

        snackbar.show();
    }

    @Override
    public <T> void launchActivity(Activity context, Class<T> clazz) {
        if (context != null) {
            Intent intent = new Intent(context, clazz);
            startActivity(intent);
            overridePendingTransition(R.anim.tran_left_in, R.anim.tran_right_out);
        }
    }

    @Override
    public <T> void launchActivity(Activity context, Class<T> clazz, int enterAnim, int exitAnim) {
        if (context != null) {
            Intent intent = new Intent(context, clazz);
            startActivity(intent);
            overridePendingTransition(enterAnim, exitAnim);
        }
    }

//    @Override
//    public void logout() {
//        SessionManager.getInstance().removeUserSession();
//        finishAffinity();
//        launchActivity(BaseActivity.this, SignUpActivity.class);
//    }

    @Override
    public void onApiFailure(ErrorResponse errorResponse) {

    }

    @Override
    public <T> void launchActivity(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.tran_left_in, R.anim.tran_right_out);
    }


//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onNetworkChange(Events.NetworkOffline networkOffline) {
//        if (!sNoInternetShow)
//            showOfflineOnline(true);
//    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onNetworkChange(Events.NetworkOnline networkOnline) {
//        showOfflineOnline(false);
//    }

//    protected void showOfflineOnline(boolean isOffline) {
//        if (sIsNoInternetSnacShow) {
//            if (null != mSnacbar)
//                mSnacbar.dismiss();
//            if (isOffline) {
//                hideProgress();
//                mWasOffline = true;
//                final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
//                mSnacbar = Snackbar.make(viewGroup, getResources().getString(R.string.network_connection_error), Snackbar.LENGTH_INDEFINITE);
//                mSnacbar.show();
//            } else {
//                if (null != mSnacbar && mWasOffline) {
//                    mWasOffline = false;
//                    final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
//                    mSnacbar = Snackbar.make(viewGroup, getResources().getString(R.string.network_connection_error), Snackbar.LENGTH_LONG);
//                    mSnacbar.show();
//                }
//            }
//        } else {
//            hideProgress();
//            if (!NoInternetActivity.sIsActive && isOffline) {
//                launchActivity(this, NoInternetActivity.class, R.anim.slide_in_up, R.anim.stay);
//            }
//        }
//    }

    protected void setHeaderTitle(int headerTitle) {

    }

    protected void setHeaderTitle(int headerTitle, TextView headerTV) {
        headerTV.setText(headerTitle);
    }
    protected void setupToolBar(Toolbar toolbar, int toolBarLayout) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(getLayoutInflater().inflate(toolBarLayout, null), new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER
        ));

    }
}