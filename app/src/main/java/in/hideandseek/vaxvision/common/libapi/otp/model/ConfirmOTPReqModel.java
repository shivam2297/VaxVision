package in.hideandseek.vaxvision.common.libapi.otp.model;

import java.io.Serializable;

public class ConfirmOTPReqModel implements Serializable {
    String otp;
    String txnId;

    public ConfirmOTPReqModel(String otp, String txnId) {
        this.otp = otp;
        this.txnId = txnId;
    }
}
