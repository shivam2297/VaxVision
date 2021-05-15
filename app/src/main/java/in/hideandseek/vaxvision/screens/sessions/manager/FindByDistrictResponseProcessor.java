package in.hideandseek.vaxvision.screens.sessions.manager;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.lib.ResponseCallback;
import in.hideandseek.vaxvision.common.libapi.sessions.model.CurrentSessionList;
import in.hideandseek.vaxvision.screens.sessions.presenter.IFindByDistrictResReceiver;

public class FindByDistrictResponseProcessor implements ResponseCallback<CurrentSessionList> {
    private IFindByDistrictResReceiver resReceiver;

    public FindByDistrictResponseProcessor(IFindByDistrictResReceiver resReceiver) {
        this.resReceiver = resReceiver;
    }

    @Override
    public void onSuccess(CurrentSessionList data) {
        resReceiver.onSuccessSessions(data.getSessions());
    }

    @Override
    public void onFailure(ErrorResponse error) {
        resReceiver.onFailure(error);
    }
}
