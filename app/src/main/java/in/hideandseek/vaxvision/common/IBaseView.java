package in.hideandseek.vaxvision.common;

import android.app.Activity;
import android.content.Intent;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;

public interface IBaseView {

    void showProgress();

    void hideProgress();

    void showSnackBar(int message);

    <T> void launchActivity(Activity context, Class<T> clazz);

    <T> void launchActivity(Activity context, Class<T> clazz, int enterAnim, int exitAnim);

    <T> void  launchActivity(Intent intent);

//    void logout();

    void onApiFailure(ErrorResponse errorResponse);
}
