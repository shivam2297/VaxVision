
package in.hideandseek.vaxvision.common.libapi.calendar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Session {

    @SerializedName("session_id")
    @Expose
    private String sessionId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("available_capacity")
    @Expose
    private Integer availableCapacity;
    @SerializedName("available_capacity_dose1")
    @Expose
    private Integer availableCapacityDose1;
    @SerializedName("available_capacity_dose2")
    @Expose
    private Integer availableCapacityDose2;
    @SerializedName("walkin_ind")
    @Expose
    private String walkinInd;
    @SerializedName("min_age_limit")
    @Expose
    private Integer minAgeLimit;
    @SerializedName("vaccine")
    @Expose
    private String vaccine;
    @SerializedName("slots")
    @Expose
    private List<String> slots = null;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(Integer availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public Integer getAvailableCapacityDose1() {
        return availableCapacityDose1;
    }

    public void setAvailableCapacityDose1(Integer availableCapacityDose1) {
        this.availableCapacityDose1 = availableCapacityDose1;
    }

    public Integer getAvailableCapacityDose2() {
        return availableCapacityDose2;
    }

    public void setAvailableCapacityDose2(Integer availableCapacityDose2) {
        this.availableCapacityDose2 = availableCapacityDose2;
    }

    public String getWalkinInd() {
        return walkinInd;
    }

    public void setWalkinInd(String walkinInd) {
        this.walkinInd = walkinInd;
    }

    public Integer getMinAgeLimit() {
        return minAgeLimit;
    }

    public void setMinAgeLimit(Integer minAgeLimit) {
        this.minAgeLimit = minAgeLimit;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public List<String> getSlots() {
        return slots;
    }

    public void setSlots(List<String> slots) {
        this.slots = slots;
    }

}
