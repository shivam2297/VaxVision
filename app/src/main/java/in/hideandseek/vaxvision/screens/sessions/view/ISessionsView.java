package in.hideandseek.vaxvision.screens.sessions.view;

import java.util.List;

import in.hideandseek.vaxvision.common.IBaseView;
import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.libapi.sessions.model.Session;

public interface ISessionsView extends IBaseView {
    void onSuccessSessions(List<Session> sessions);

    void onFailure(ErrorResponse errorResponse);
}
