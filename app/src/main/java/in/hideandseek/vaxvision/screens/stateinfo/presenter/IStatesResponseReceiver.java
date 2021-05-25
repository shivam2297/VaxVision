package in.hideandseek.vaxvision.screens.stateinfo.presenter;

import java.util.List;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.libapi.statedistrict.model.State;

public interface IStatesResponseReceiver {

    void onSuccessState(List<State> states);

    void onFailure(ErrorResponse errorResponse);

}
