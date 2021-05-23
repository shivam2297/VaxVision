package in.hideandseek.vaxvision.screens.sessions.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hideandseek.vaxvision.R;
import in.hideandseek.vaxvision.common.BaseActivity;
import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.libapi.sessions.model.Session;
import in.hideandseek.vaxvision.common.libapi.sessions.request.FindByDistrictApiRequest;
import in.hideandseek.vaxvision.screens.sessions.adapter.SessionsAdapter;
import in.hideandseek.vaxvision.screens.sessions.manager.SessionsServiceManager;
import in.hideandseek.vaxvision.screens.sessions.presenter.ISessionsPresenter;
import in.hideandseek.vaxvision.screens.sessions.presenter.SessionsPresenterImpl;

public class SessionsActivity extends BaseActivity implements ISessionsView, SessionsAdapter.OnSessionItemClickListener {

    @BindView(R.id.rv_sessions)
    RecyclerView mSessionsRecyclerView;

    @BindView(R.id.tv_no_items)
    AppCompatTextView mNoItemTv;

    public static final String KEY_DISTRICT_ID = "KEY_DISTRICT_ID";
    public static final String KEY_DATE = "KEY_DATE";

    private ArrayList<Session> mSessions = new ArrayList<Session>();
    private ISessionsPresenter mPresenter;
    private SessionsAdapter mSessionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions);
        ButterKnife.bind(this);

        mPresenter = new SessionsPresenterImpl(new SessionsServiceManager(new FindByDistrictApiRequest()));
        mPresenter.onViewBeingCreated(this);

        mSessionAdapter = new SessionsAdapter(mSessions, this);
        mSessionsRecyclerView.setAdapter(mSessionAdapter);
        mSessionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        mSessionAdapter.notifyDataSetChanged();

        if (mSessions.size() == 0) {
            mNoItemTv.setVisibility(View.VISIBLE);
            mSessionsRecyclerView.setVisibility(View.INVISIBLE);
        } else {
            mNoItemTv.setVisibility(View.INVISIBLE);
            mSessionsRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFailure(ErrorResponse errorResponse) {
        showSnackBar(errorResponse.getErrorMessage());
        if (mSessions.size() == 0) {
            mNoItemTv.setVisibility(View.VISIBLE);
            mSessionsRecyclerView.setVisibility(View.INVISIBLE);
        } else {
            mNoItemTv.setVisibility(View.INVISIBLE);
            mSessionsRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void locateOnMap(int position) {
        String locationAndAddress = mSessions.get(position)
                .getName()
                + ", "
                + mSessions.get(position)
                .getAddress();
        String latLongQuery = "geo:"
                + mSessions.get(position).getLat().toString()
                + ","
                + mSessions.get(position).getLong().toString()
                + "?z=10&"
                + "q="
                + Uri.encode(locationAndAddress);
        Uri gmmIntentUri = Uri.parse(latLongQuery);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
//        if (mapIntent.resolveActivity(getPackageManager()) != null)
//            startActivity(mapIntent);
//        else
//            showSnackBar(R.string.install_maps_msg);

    }

    @Override
    public void registerOnCowin(int position) {
        String url = "https://www.cowin.gov.in/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}