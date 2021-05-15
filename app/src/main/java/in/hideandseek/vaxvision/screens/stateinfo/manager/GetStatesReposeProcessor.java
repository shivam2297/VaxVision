package in.hideandseek.vaxvision.screens.stateinfo.manager;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.lib.ResponseCallback;
import in.hideandseek.vaxvision.common.lib.ResponseModel;
import in.hideandseek.vaxvision.common.libapi.statedistrict.model.StateList;
import in.hideandseek.vaxvision.screens.stateinfo.presenter.IStatesResponseReceiver;

public class GetStatesReposeProcessor implements ResponseCallback<StateList> {

    private IStatesResponseReceiver responseReceiver;

    public GetStatesReposeProcessor(IStatesResponseReceiver responseReceiver) {
        this.responseReceiver = responseReceiver;
    }

    @Override
    public void onSuccess(StateList data) {
        responseReceiver.onSuccessState(data.getStates());
    }

    @Override
    public void onFailure(ErrorResponse error) {
        responseReceiver.onFailure(error);
    }
}
