package in.hideandseek.vaxvision.screens.downloadcert.view;

import in.hideandseek.vaxvision.common.IBaseView;
import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import okhttp3.ResponseBody;

public interface ICertificateView extends IBaseView {

    void onCertDownloadSuccess(ResponseBody data);
}
