package in.hideandseek.vaxvision.common.libapi.statedistrict.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DistrictList {

    @SerializedName("districts")
    @Expose
    private List<District> districts = null;
    @SerializedName("ttl")
    @Expose
    private Integer ttl;

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

}
