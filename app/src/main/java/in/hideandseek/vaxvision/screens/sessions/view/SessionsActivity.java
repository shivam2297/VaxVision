package in.hideandseek.vaxvision.screens.sessions.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import in.hideandseek.vaxvision.R;
import in.hideandseek.vaxvision.common.BaseActivity;
import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.libapi.sessions.model.Session;

public class SessionsActivity extends BaseActivity implements ISessionsView {

    private ArrayList<Session> mSessions = new ArrayList<Session>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions);
        ButterKnife.bind(this);
    }

    @Override
    public void onSuccessSessions(List<Session> sessions) {
        mSessions.clear();
        mSessions.addAll(sessions);
    }

    @Override
    public void onFailure(ErrorResponse errorResponse) {
        showSnackBar(errorResponse.getErrorMessage());
    }
}