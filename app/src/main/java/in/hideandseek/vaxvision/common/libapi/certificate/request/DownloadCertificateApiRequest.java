package in.hideandseek.vaxvision.common.libapi.certificate.request;

import in.hideandseek.vaxvision.common.lib.ErrorMessageResolver;
import in.hideandseek.vaxvision.common.lib.ResponseCallback;
import in.hideandseek.vaxvision.common.lib.ResponseWrapper;
import in.hideandseek.vaxvision.common.lib.ServiceManager;
import in.hideandseek.vaxvision.common.libapi.certificate.service.IDownloadCertService;
import in.hideandseek.vaxvision.common.libapi.sessions.model.CurrentSessionList;
import in.hideandseek.vaxvision.common.libapi.sessions.service.IFindByDistrictService;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class DownloadCertificateApiRequest {
    private Call<ResponseBody> mDownloadCertCall;

    public void makeRequest(ResponseCallback<ResponseBody> responseCallBack, ErrorMessageResolver errorMessageResolver, String refID, String token) {
        IDownloadCertService downloadCertService = ServiceManager.getManager().createService(IDownloadCertService.class);
        token = "Bearer " + token;
        mDownloadCertCall = downloadCertService.download(token, refID);
        mDownloadCertCall.enqueue(new ResponseWrapper<>(responseCallBack, errorMessageResolver));
    }
}
