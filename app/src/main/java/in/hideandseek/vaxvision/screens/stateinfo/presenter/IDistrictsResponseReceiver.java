package in.hideandseek.vaxvision.screens.stateinfo.presenter;

import java.util.List;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.libapi.statedistrict.model.District;

public interface IDistrictsResponseReceiver {

    void onSuccess(List<District> districts);
    void onFailure(ErrorResponse errorResponse);
}
