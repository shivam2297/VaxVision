package in.hideandseek.vaxvision.screens.otp.presenter;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.screens.otp.manager.OtpServiceManager;
import in.hideandseek.vaxvision.screens.otp.view.IOtpView;

public class OtpPresenterImpl implements IOtpPresenter, IGenerateOtpResReceiver, IConfirmOtpResReceiver {

    private IOtpView mView;
    private OtpServiceManager manager;

    public OtpPresenterImpl(OtpServiceManager manager) {
        this.manager = manager;
    }

    @Override
    public void onOtpConfirmed(String token) {
        if (mView != null) {
            mView.hideProgress();
            mView.onOtpConfirmed(token);
        }
    }

    @Override
    public void onSuccessGenerateOtp(String txnId) {
        if (mView != null) {
            mView.hideProgress();
            mView.onSuccessGenerateOtp(txnId);
        }
    }

    @Override
    public void onFailure(ErrorResponse errorResponse) {
        if (mView != null) {
            mView.hideProgress();
            mView.onFailure(errorResponse);
        }
    }

    @Override
    public void generateOtp(String mobile) {
        if (mView != null) {
            mView.showProgress();
        }
        manager.generateOtp(this, mobile);
    }

    @Override
    public void confirmOtp(String otp, String txnId) {
        if (mView != null)
            mView.showProgress();
        manager.confirmOtp(this, getOtpData(otp), txnId);
    }

    @Override
    public void onViewBeingCreated(IOtpView view) {
        mView = view;
    }

    @Override
    public void onViewBeingDestroyed() {
        if (mView != null)
            mView = null;
    }

    private String getOtpData(String otp) {
        String messageToEncode = otp;
        byte[] hmacSha256 = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec("ApiConstants.API_SECRET_KEY".getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(secretKeySpec);
            hmacSha256 = mac.doFinal(messageToEncode.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }
        return String.format("%0" + (hmacSha256.length*2) + "X", new BigInteger(1, hmacSha256)).toLowerCase();
    }
}
