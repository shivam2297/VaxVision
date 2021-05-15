package in.hideandseek.vaxvision.common.libapi.sessions.request;

import in.hideandseek.vaxvision.common.lib.ErrorMessageResolver;
import in.hideandseek.vaxvision.common.lib.ResponseCallback;
import in.hideandseek.vaxvision.common.lib.ResponseWrapper;
import in.hideandseek.vaxvision.common.lib.ServiceManager;
import in.hideandseek.vaxvision.common.libapi.sessions.model.CurrentSessionList;
import in.hideandseek.vaxvision.common.libapi.sessions.service.IFindByDistrictService;
import in.hideandseek.vaxvision.common.libapi.statedistrict.model.DistrictList;
import in.hideandseek.vaxvision.common.libapi.statedistrict.service.IGetDistrictsService;
import retrofit2.Call;

public class FindByDistrictApiRequest {
    private Call<CurrentSessionList> mFindByDistrictCall;

    public void makeRequest(ResponseCallback<CurrentSessionList> responseCallBack, ErrorMessageResolver errorMessageResolver, int districtID, String date) {
        IFindByDistrictService findByDistrictService = ServiceManager.getManager().createService(IFindByDistrictService.class);
        mFindByDistrictCall = findByDistrictService.getCurrentSessions(districtID, date);
        mFindByDistrictCall.enqueue(new ResponseWrapper<>(responseCallBack, errorMessageResolver));
    }
}
