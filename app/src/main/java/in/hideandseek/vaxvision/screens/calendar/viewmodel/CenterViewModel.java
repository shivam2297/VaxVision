package in.hideandseek.vaxvision.screens.calendar.viewmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import in.hideandseek.vaxvision.common.libapi.calendar.model.Session;
import in.hideandseek.vaxvision.common.libapi.calendar.model.VaccineFee;

public class CenterViewModel {

    public String name;

    public String address;

    public String stateName;

    public String districtName;

    public String blockName;

    public String pincode;

    public Double lat;

    public Double _long;

    public String feeType;

    public List<VaccineFee> vaccineFees = null;

    /*seven arrays of sessions corresponding to seven days*/
    public List<List<Session>> sessions = new ArrayList<List<Session>>();

    public CenterViewModel() {
        sessions.add(new ArrayList<Session>());
        sessions.add(new ArrayList<Session>());
        sessions.add(new ArrayList<Session>());
        sessions.add(new ArrayList<Session>());
        sessions.add(new ArrayList<Session>());
        sessions.add(new ArrayList<Session>());
        sessions.add(new ArrayList<Session>());
    }

    public CenterViewModel(String name, String address, String stateName, String districtName, String blockName, String pincode, Double lat, Double _long, String feeType, List<VaccineFee> vaccineFees) {
        this.name = name;
        this.address = address;
        this.stateName = stateName;
        this.districtName = districtName;
        this.blockName = blockName;
        this.pincode = pincode;
        this.lat = lat;
        this._long = _long;
        this.feeType = feeType;
        this.vaccineFees = vaccineFees;
        // add seven array lists for seven dates
        this.sessions.add(new ArrayList<Session>());
        this.sessions.add(new ArrayList<Session>());
        this.sessions.add(new ArrayList<Session>());
        this.sessions.add(new ArrayList<Session>());
        this.sessions.add(new ArrayList<Session>());
        this.sessions.add(new ArrayList<Session>());
        this.sessions.add(new ArrayList<Session>());
    }
}
