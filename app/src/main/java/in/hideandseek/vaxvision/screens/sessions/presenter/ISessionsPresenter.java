package in.hideandseek.vaxvision.screens.sessions.presenter;

import in.hideandseek.vaxvision.common.IBasePresenter;
import in.hideandseek.vaxvision.screens.sessions.view.ISessionsView;

public interface ISessionsPresenter extends IBasePresenter<ISessionsView> {

    void findSessionsByDistrict(int districtId, String date);
}
