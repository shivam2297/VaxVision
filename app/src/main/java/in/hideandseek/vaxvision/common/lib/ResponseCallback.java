package in.hideandseek.vaxvision.common.lib;

public interface ResponseCallback<T> {

    void onSuccess(T data);

    void onFailure(ErrorResponse error);
}
