package in.hideandseek.vaxvision.screens.stateinfo.presenter;

import in.hideandseek.vaxvision.common.IBasePresenter;
import in.hideandseek.vaxvision.screens.stateinfo.view.ISelectStateView;

public interface ISelectStatePresenter extends IBasePresenter<ISelectStateView> {

    void getStates();

    void getDistricts(int stateID);
}
