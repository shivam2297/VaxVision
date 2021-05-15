package in.hideandseek.vaxvision.common.lib;

import java.io.Serializable;
import java.util.List;

public class ResponseModel<T> implements Serializable {
    private String total;

    private String code;

    private List<T> data;

    private String count;

    private String lastID;

    private String message;

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public List<T> getData ()
    {
        return data;
    }

    public void setData (List<T> data)
    {
        this.data = data;
    }

    public String getCount ()
    {
        return count;
    }

    public void setCount (String count)
    {
        this.count = count;
    }

    public String getLastID ()
    {
        return lastID;
    }

    public void setLastID (String lastID)
    {
        this.lastID = lastID;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [total = "+total+", code = "+code+", data = "+data+", count = "+count+", lastID = "+lastID+", message = "+message+"]";
    }
}