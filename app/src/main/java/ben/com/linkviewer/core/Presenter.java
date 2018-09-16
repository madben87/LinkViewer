package ben.com.linkviewer.core;

public interface Presenter<V extends MVPView> {
    void attachView(V v);
    void detachView();
}
