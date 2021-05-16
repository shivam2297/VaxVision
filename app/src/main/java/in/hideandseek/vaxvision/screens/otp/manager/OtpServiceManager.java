package in.hideandseek.vaxvision.screens.otp.manager;

import in.hideandseek.vaxvision.common.lib.ErrorMessageResolver;
import in.hideandseek.vaxvision.common.libapi.otp.request.ConfirmOtpApiRequest;
import in.hideandseek.vaxvision.common.libapi.otp.request.GenerateOtpApiRequest;
import in.hideandseek.vaxvision.screens.otp.presenter.IConfirmOtpResReceiver;
import in.hideandseek.vaxvision.screens.otp.presenter.IGenerateOtpResReceiver;

public class OtpServiceManager {

    private GenerateOtpApiRequest generateOtpApiRequest;
    private ConfirmOtpApiRequest confirmOtpApiRequest;

    public OtpServiceManager(GenerateOtpApiRequest generateOtpApiRequest, ConfirmOtpApiRequest confirmOtpApiRequest) {
        this.generateOtpApiRequest = generateOtpApiRequest;
        this.confirmOtpApiRequest = confirmOtpApiRequest;
    }

    public void generateOtp(IGenerateOtpResReceiver resReceiver, String mobile) {
        generateOtpApiRequest.makeRequest(new GenerateOtpResProcessor(resReceiver), ErrorMessageResolver.getStandardErrorResolver(), mobile);
    }

    public void confirmOtp(IConfirmOtpResReceiver resReceiver, String otpData, String txnID) {
        confirmOtpApiRequest.makeRequest(new ConfirmOtpResProcessor(resReceiver), ErrorMessageResolver.getStandardErrorResolver(), otpData, txnID);
    }
}
