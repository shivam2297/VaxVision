package in.hideandseek.vaxvision.common;

public interface IBasePresenter<V> {

    void onViewBeingCreated(V view);

    void onViewBeingDestroyed();
}
