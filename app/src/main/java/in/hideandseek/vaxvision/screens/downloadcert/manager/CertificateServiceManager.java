package in.hideandseek.vaxvision.screens.downloadcert.manager;

import in.hideandseek.vaxvision.common.lib.ErrorMessageResolver;
import in.hideandseek.vaxvision.common.libapi.certificate.request.DownloadCertificateApiRequest;
import in.hideandseek.vaxvision.screens.downloadcert.presenter.ICertificateResReceiver;

public class CertificateServiceManager {

    private DownloadCertificateApiRequest apiRequest;

    public CertificateServiceManager(DownloadCertificateApiRequest apiRequest) {
        this.apiRequest = apiRequest;
    }

    public void downloadCertificate(ICertificateResReceiver resReceiver, String token, String refID) {
        apiRequest.makeRequest(new CertResProcessor(resReceiver), ErrorMessageResolver.getStandardErrorResolver(), refID, token);
    }
}
