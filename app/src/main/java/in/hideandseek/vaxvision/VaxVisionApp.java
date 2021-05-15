package in.hideandseek.vaxvision;

import android.app.Application;

public class VaxVisionApp extends Application {
    private static VaxVisionApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static synchronized VaxVisionApp getInstance() {
        return mInstance;
    }
}
