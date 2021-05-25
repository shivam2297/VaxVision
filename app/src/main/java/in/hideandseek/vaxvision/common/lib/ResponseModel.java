package in.hideandseek.vaxvision.common.lib;

import java.io.Serializable;
import java.util.List;

public class ResponseModel<T> implements Serializable {

    private T data;

    public T getData ()
    {
        return data;
    }

    public void setData (T data)
    {
        this.data = data;
    }
}