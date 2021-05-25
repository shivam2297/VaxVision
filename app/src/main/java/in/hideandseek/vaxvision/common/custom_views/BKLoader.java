package in.hideandseek.vaxvision.common.custom_views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import in.hideandseek.vaxvision.R;


public class BKLoader {

    private Dialog mDialog;
    private static BKLoader sInstance;
    private BKLoader() {

    }

    public static BKLoader getInstance() {
        if (sInstance == null)
            sInstance = new BKLoader();
        return sInstance;
    }

    public void show(Context context) {
        if (mDialog != null && mDialog.isShowing())
            return;

        mDialog = new Dialog(context);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.proggress_bar_view);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setCancelable(false);
        mDialog.show();
    }

    public void dismiss(){
        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();
    }
}
