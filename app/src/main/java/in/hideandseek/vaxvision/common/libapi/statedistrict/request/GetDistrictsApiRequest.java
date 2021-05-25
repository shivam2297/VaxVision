package in.hideandseek.vaxvision.common.libapi.statedistrict.request;

import android.app.Service;

import in.hideandseek.vaxvision.common.lib.ErrorMessageResolver;
import in.hideandseek.vaxvision.common.lib.ResponseCallback;
import in.hideandseek.vaxvision.common.lib.ResponseModel;
import in.hideandseek.vaxvision.common.lib.ResponseWrapper;
import in.hideandseek.vaxvision.common.lib.ServiceManager;
import in.hideandseek.vaxvision.common.libapi.statedistrict.model.DistrictList;
import in.hideandseek.vaxvision.common.libapi.statedistrict.model.StateList;
import in.hideandseek.vaxvision.common.libapi.statedistrict.service.IGetDistrictsService;
import in.hideandseek.vaxvision.common.libapi.statedistrict.service.IGetStateService;
import retrofit2.Call;

public class GetDistrictsApiRequest {
    private Call<DistrictList> mGetDistrictsCall;

    public void makeRequest(ResponseCallback<DistrictList> responseCallBack, ErrorMessageResolver errorMessageResolver, int stateID) {
        IGetDistrictsService iGetDistrictsService = ServiceManager.getManager().createService(IGetDistrictsService.class);
        mGetDistrictsCall = iGetDistrictsService.getDistricts(stateID);
        mGetDistrictsCall.enqueue(new ResponseWrapper<>(responseCallBack, errorMessageResolver));
    }
}
