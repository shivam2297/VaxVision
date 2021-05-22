package in.hideandseek.vaxvision.common.libapi.otp.model;

import java.io.Serializable;

public class GenerateOtpReqModel implements Serializable {

    String mobile;

    public GenerateOtpReqModel(String mobile) {
        this.mobile = mobile;
    }
}
