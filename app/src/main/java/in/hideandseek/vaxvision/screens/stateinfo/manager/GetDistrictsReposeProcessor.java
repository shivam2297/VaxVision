package in.hideandseek.vaxvision.screens.stateinfo.manager;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.lib.ResponseCallback;
import in.hideandseek.vaxvision.common.lib.ResponseModel;
import in.hideandseek.vaxvision.common.libapi.statedistrict.model.DistrictList;
import in.hideandseek.vaxvision.common.libapi.statedistrict.model.StateList;
import in.hideandseek.vaxvision.screens.stateinfo.presenter.IDistrictsResponseReceiver;
import in.hideandseek.vaxvision.screens.stateinfo.presenter.IStatesResponseReceiver;

public class GetDistrictsReposeProcessor implements ResponseCallback<DistrictList> {

    private IDistrictsResponseReceiver responseReceiver;

    public GetDistrictsReposeProcessor(IDistrictsResponseReceiver responseReceiver) {
        this.responseReceiver = responseReceiver;
    }


    @Override
    public void onSuccess(DistrictList data) {
        responseReceiver.onSuccess(data.getDistricts());
    }

    @Override
    public void onFailure(ErrorResponse error) {
        responseReceiver.onFailure(error);
    }
}
