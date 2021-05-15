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
        if (mView != null)
            mView.showProgress();
        manager.getStates(this);
    }

    @Override
    public void getDistricts(int stateID) {

        if (mView != null)
            mView.showProgress();
        manager.getDistricts(this, stateID);

    }

    @Override
    public void onSuccess(List<District> districts) {
        if (mView != null) {
            mView.hideProgress();
            mView.onSuccessDistricts(districts);
        }

    }

    @Override
    public void onSuccessState(List<State> states) {
        if (mView != null) {
            mView.hideProgress();
            mView.onSuccessStates(states);
        }
    }

    @Override
    public void onFailure(ErrorResponse errorResponse) {
        if (mView != null) {
            mView.hideProgress();
            mView.onApiFailure(errorResponse);
        }

    }
}
