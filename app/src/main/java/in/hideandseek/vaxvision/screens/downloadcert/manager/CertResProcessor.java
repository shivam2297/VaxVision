package in.hideandseek.vaxvision.screens.downloadcert.manager;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.lib.ResponseCallback;
import in.hideandseek.vaxvision.screens.downloadcert.presenter.ICertificateResReceiver;
import okhttp3.ResponseBody;

public class CertResProcessor implements ResponseCallback<ResponseBody> {
    private ICertificateResReceiver resReceiver;

    public CertResProcessor(ICertificateResReceiver resReceiver) {
        this.resReceiver = resReceiver;
    }

    @Override
    public void onSuccess(ResponseBody data) {
        resReceiver.onSuccessCertificate(data);
    }

    @Override
    public void onFailure(ErrorResponse error) {
        resReceiver.onFailure(error);
    }
}
