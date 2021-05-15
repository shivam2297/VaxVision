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
import in.hideandseek.vaxvision.common.libapi.sessions.request.FindByDistrictApiRequest;
import in.hideandseek.vaxvision.screens.sessions.manager.SessionsServiceManager;
import in.hideandseek.vaxvision.screens.sessions.presenter.ISessionsPresenter;
import in.hideandseek.vaxvision.screens.sessions.presenter.SessionsPresenterImpl;

public class SessionsActivity extends BaseActivity implements ISessionsView {


    public static final String KEY_DISTRICT_ID = "KEY_DISTRICT_ID";
    public static final String KEY_DATE = "KEY_DATE";

    private ArrayList<Session> mSessions = new ArrayList<Session>();
    private ISessionsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions);
        ButterKnife.bind(this);

        mPresenter = new SessionsPresenterImpl(new SessionsServiceManager(new FindByDistrictApiRequest()));
        mPresenter.onViewBeingCreated(this);

        int districtID = getIntent().getIntExtra(SessionsActivity.KEY_DISTRICT_ID, 0);
        String date = getIntent().getStringExtra(SessionsActivity.KEY_DATE);
        mPresenter.findSessionsByDistrict(districtID, date);

    }

    @Override
    protected void onDestroy() {
        mPresenter.onViewBeingDestroyed();
        super.onDestroy();
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