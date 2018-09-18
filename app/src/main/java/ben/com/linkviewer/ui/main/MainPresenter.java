package ben.com.linkviewer.ui.main;

import ben.com.linkviewer.core.MVPView;
import ben.com.linkviewer.core.Presenter;
import ben.com.linkviewer.model.LinkModel;

public interface MainPresenter<V extends MVPView> extends Presenter<V> {

    void needShowLink(LinkModel model);
    void needShowHistoryLink(LinkModel model);
}
