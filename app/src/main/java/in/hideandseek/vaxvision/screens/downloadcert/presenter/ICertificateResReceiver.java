package in.hideandseek.vaxvision.screens.downloadcert.presenter;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import okhttp3.ResponseBody;

public interface ICertificateResReceiver {

    void onSuccessCertificate(ResponseBody pdfBinary);
    void onFailure(ErrorResponse errorResponse);
}
