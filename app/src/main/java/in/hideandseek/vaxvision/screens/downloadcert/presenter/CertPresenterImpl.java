package in.hideandseek.vaxvision.screens.downloadcert.presenter;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.screens.downloadcert.manager.CertificateServiceManager;
import in.hideandseek.vaxvision.screens.downloadcert.view.ICertificateView;
import okhttp3.ResponseBody;

public class CertPresenterImpl implements ICertPresenter, ICertificateResReceiver {

    private ICertificateView mView;
    private CertificateServiceManager mManager;

    public CertPresenterImpl(CertificateServiceManager mManager) {
        this.mManager = mManager;
    }

    @Override
    public void onViewBeingCreated(ICertificateView view) {
        mView = view;
    }

    @Override
    public void onViewBeingDestroyed() {
        if (mView != null)
            mView = null;
    }

    @Override
    public void downloadCert(String refID, String token) {
        if (mView != null)
            mView.showProgress();
        mManager.downloadCertificate(this, token, refID);
    }

    @Override
    public void onSuccessCertificate(ResponseBody pdfBinary) {
        if (mView != null) {
            mView.hideProgress();
            mView.onCertDownloadSuccess(pdfBinary);
        }
    }

    @Override
    public void onFailure(ErrorResponse errorResponse) {
        if (mView != null) {
            mView.hideProgress();
            mView.onApiFailure(errorResponse);
        }
    }
}
