package in.hideandseek.vaxvision.common.eventbus;

import org.greenrobot.eventbus.EventBus;

public class GlobalBus {

    private static EventBus sBus;

    public static EventBus getsBus() {
        if (sBus == null)
            sBus = EventBus.getDefault();
        return sBus;
    }
}
