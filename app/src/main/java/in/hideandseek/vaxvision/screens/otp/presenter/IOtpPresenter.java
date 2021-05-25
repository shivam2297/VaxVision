package in.hideandseek.vaxvision.screens.otp.presenter;

import in.hideandseek.vaxvision.common.IBasePresenter;
import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.screens.otp.view.IOtpView;

public interface IOtpPresenter extends IBasePresenter<IOtpView> {

    void generateOtp(String mobile);


    void confirmOtp(String otp, String txnId);
}
