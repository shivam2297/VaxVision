package in.hideandseek.vaxvision.screens.downloadcert.presenter;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;

public interface ICertificateResReceiver {

    void onSuccessCertificate(String pdfBinary);
    void onFailure(ErrorResponse errorResponse);
}
