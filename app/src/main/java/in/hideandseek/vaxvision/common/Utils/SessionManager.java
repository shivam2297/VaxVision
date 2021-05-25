package in.hideandseek.vaxvision.common.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private final String USER_PREFERENCE_FILE_NAME = "USER_PREFERENCE_FILE_NAME";
    private final String USER_ID_KEY = "USER_ID_KEY";

    private static SessionManager sInstance;
    private SharedPreferences mSharedPreferences;


    // singleton
    private SessionManager() {

    }

    public void initialize(Context context) {
        mSharedPreferences = context.getSharedPreferences(USER_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
    }

    public static SessionManager getInstance() {
        if (sInstance == null)
            sInstance = new SessionManager();
        return sInstance;
    }

    public void saveUserSession(String uid) {
        // save the user id to preference
        checkInitialization();
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(USER_ID_KEY, uid).apply();
    }

    public void removeUserSession() {
        checkInitialization();

        try {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.clear().apply();
        } catch (Exception e) {

        }
    }

    public String getUserID() {
        checkInitialization();
        String uid = "";
        try {
            uid = mSharedPreferences.getString(USER_ID_KEY, "");
        } catch (Exception e) {

        }

        return uid;
    }

    public boolean isCurrentUserLogged() {
        return mSharedPreferences != null && mSharedPreferences.getString(USER_ID_KEY, "") != null && !"".equals(mSharedPreferences.getString(USER_ID_KEY, ""));
    }

    public void checkInitialization() {
        if (mSharedPreferences == null)
            throw new IllegalStateException("shared preferences not yet initialized");
    }
}
