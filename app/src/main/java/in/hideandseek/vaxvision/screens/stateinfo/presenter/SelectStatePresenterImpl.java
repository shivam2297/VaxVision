package in.hideandseek.vaxvision.screens.stateinfo.presenter;

import java.util.List;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.libapi.statedistrict.model.District;
import in.hideandseek.vaxvision.common.libapi.statedistrict.model.State;
import in.hideandseek.vaxvision.screens.stateinfo.manager.StateDistrictServiceManager;
import in.hideandseek.vaxvision.screens.stateinfo.view.ISelectStateView;

public class SelectStatePresenterImpl implements ISelectStatePresenter, IStatesResponseReceiver, IDistrictsResponseReceiver {

    private ISelectStateView mView;
    private StateDistrictServiceManager manager;

    public SelectStatePresenterImpl(StateDistrictServiceManager manager) {
        this.manager = manager;
    }

    @Override
    public void onViewBeingCreated(ISelectStateView view) {
        this.mView = view;
    }

    @Override
    public void onViewBeingDestroyed() {
        if (mView != null)
            mView = null;
    }

    @Override
    public void getStates() {

    }

    @Override
    public void getDistricts(int stateID) {

    }

    @Override
    public void onSuccess(List<District> districts) {

    }

    @Override
    public void onSuccessState(List<State> states) {

    }

    @Override
    public void onFailure(ErrorResponse errorResponse) {
        mView.onApiFailure(errorResponse);
    }
}
