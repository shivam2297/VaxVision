package in.hideandseek.vaxvision.common.libapi.statedistrict.request;

import in.hideandseek.vaxvision.common.lib.ErrorMessageResolver;
import in.hideandseek.vaxvision.common.lib.ResponseCallback;
import in.hideandseek.vaxvision.common.lib.ResponseModel;
import in.hideandseek.vaxvision.common.lib.ResponseWrapper;
import in.hideandseek.vaxvision.common.lib.ServiceManager;
import in.hideandseek.vaxvision.common.libapi.statedistrict.model.StateList;
import in.hideandseek.vaxvision.common.libapi.statedistrict.service.IGetStateService;
import retrofit2.Call;

public class GetStatesApiRequest {
    private Call<StateList> mGetStatesCall;

    public void makeRequest(ResponseCallback<StateList> responseCallBack, ErrorMessageResolver errorMessageResolver) {
        IGetStateService iGetStateService = ServiceManager.getManager().createService(IGetStateService.class);
        mGetStatesCall = iGetStateService.getStates();
        mGetStatesCall.enqueue(new ResponseWrapper<>(responseCallBack, errorMessageResolver));
    }
}
