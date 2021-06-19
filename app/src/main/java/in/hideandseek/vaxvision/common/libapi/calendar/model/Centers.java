
package in.hideandseek.vaxvision.common.libapi.calendar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Centers {

    @SerializedName("centers")
    @Expose
    private List<Center> centers = null;

    public List<Center> getCenters() {
        return centers;
    }

    public void setCenters(List<Center> centers) {
        this.centers = centers;
    }

}
