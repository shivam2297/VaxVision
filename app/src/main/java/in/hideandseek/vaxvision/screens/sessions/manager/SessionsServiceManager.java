package in.hideandseek.vaxvision.screens.sessions.manager;

import in.hideandseek.vaxvision.common.lib.ErrorMessageResolver;
import in.hideandseek.vaxvision.common.libapi.sessions.request.FindByDistrictApiRequest;
import in.hideandseek.vaxvision.screens.sessions.presenter.IFindByDistrictResReceiver;

public class SessionsServiceManager {

    private FindByDistrictApiRequest findByDistrictApiRequest;

    public SessionsServiceManager(FindByDistrictApiRequest findByDistrictApiRequest) {
        this.findByDistrictApiRequest = findByDistrictApiRequest;
    }

    public void findByDistrict(IFindByDistrictResReceiver resReceiver, int districtId, String date) {
        findByDistrictApiRequest.makeRequest(new FindByDistrictResponseProcessor(resReceiver), ErrorMessageResolver.getStandardErrorResolver(), districtId, date);
    }
}
