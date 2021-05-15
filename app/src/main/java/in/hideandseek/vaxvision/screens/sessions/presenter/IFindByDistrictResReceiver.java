package in.hideandseek.vaxvision.screens.sessions.presenter;

import java.util.List;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.libapi.sessions.model.Session;

public interface IFindByDistrictResReceiver {

    void onSuccessSessions(List<Session> sessions);
    void onFailure(ErrorResponse errorResponse);
}
