package in.hideandseek.vaxvision.screens.sessions.presenter;

import java.util.List;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.libapi.sessions.model.Session;
import in.hideandseek.vaxvision.screens.sessions.manager.SessionsServiceManager;
import in.hideandseek.vaxvision.screens.sessions.view.ISessionsView;

public class SessionsPresenterImpl implements ISessionsPresenter, IFindByDistrictResReceiver {
    private ISessionsView mView;
    private SessionsServiceManager manager;

    public SessionsPresenterImpl(SessionsServiceManager manager) {
        this.manager = manager;
    }

    public void findSessionsByDistrict(int districtId, String date) {
        if (mView != null)
            mView.showProgress();
        manager.findByDistrict(this, districtId, date);
    }

    @Override
    public void onViewBeingCreated(ISessionsView view) {
        this.mView = view;
    }

    @Override
    public void onViewBeingDestroyed() {
        if (mView != null)
            mView = null;
    }

    @Override
    public void onSuccessSessions(List<Session> sessions) {
        if (mView != null) {
            mView.hideProgress();
            mView.onSuccessSessions(sessions);
        }
    }

    @Override
    public void onFailure(ErrorResponse errorResponse) {
        if (mView != null) {
            mView.hideProgress();
            mView.onFailure(errorResponse);
        }
    }
}
