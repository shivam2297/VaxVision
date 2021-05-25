package in.hideandseek.vaxvision.common.libapi.statedistrict.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class District {

    @SerializedName("state_id")
    @Expose
    private Integer stateId;
    @SerializedName("district_id")
    @Expose
    private Integer districtId;
    @SerializedName("district_name")
    @Expose
    private String districtName;
    @SerializedName("district_name_l")
    @Expose
    private String districtNameL;

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictNameL() {
        return districtNameL;
    }

    public void setDistrictNameL(String districtNameL) {
        this.districtNameL = districtNameL;
    }

}
