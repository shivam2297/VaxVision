package in.hideandseek.vaxvision.common.libapi.otp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransIdModel {

    @SerializedName("txnId")
    @Expose
    private String txnId;

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

}