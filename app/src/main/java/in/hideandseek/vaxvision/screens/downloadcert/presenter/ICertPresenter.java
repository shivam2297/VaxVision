package in.hideandseek.vaxvision.screens.downloadcert.presenter;

import in.hideandseek.vaxvision.common.IBasePresenter;
import in.hideandseek.vaxvision.screens.downloadcert.view.ICertificateView;

public interface ICertPresenter extends IBasePresenter<ICertificateView> {

    void downloadCert(String refID, String token);
}
