package in.hideandseek.vaxvision.common.libapi.sessions.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentSessionList {

    @SerializedName("sessions")
    @Expose
    private List<Session> sessions = null;

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

}
