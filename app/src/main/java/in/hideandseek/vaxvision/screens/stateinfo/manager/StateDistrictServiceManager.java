package in.hideandseek.vaxvision.screens.stateinfo.manager;

import in.hideandseek.vaxvision.common.lib.ErrorMessageResolver;
import in.hideandseek.vaxvision.common.libapi.statedistrict.request.GetDistrictsApiRequest;
import in.hideandseek.vaxvision.common.libapi.statedistrict.request.GetStatesApiRequest;
import in.hideandseek.vaxvision.screens.stateinfo.presenter.IDistrictsResponseReceiver;
import in.hideandseek.vaxvision.screens.stateinfo.presenter.IStatesResponseReceiver;

public class StateDistrictServiceManager {

    private GetStatesApiRequest getStatesApiRequest;
    private GetDistrictsApiRequest getDistrictsApiRequest;

    public StateDistrictServiceManager(GetStatesApiRequest getStatesApiRequest, GetDistrictsApiRequest getDistrictsApiRequest) {
        this.getStatesApiRequest = getStatesApiRequest;
        this.getDistrictsApiRequest = getDistrictsApiRequest;
    }


    public void getStates(IStatesResponseReceiver responseReceiver) {
        getStatesApiRequest.makeRequest(new GetStatesReposeProcessor(responseReceiver), ErrorMessageResolver.getStandardErrorResolver());
    }

    public void getDistricts(IDistrictsResponseReceiver responseReceiver, int stateID) {
        getDistrictsApiRequest.makeRequest(new GetDistrictsReposeProcessor(responseReceiver), ErrorMessageResolver.getStandardErrorResolver(), stateID);
    }
}
