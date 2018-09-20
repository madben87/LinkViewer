package ben.com.linkviewer.ui.main;

import android.widget.ImageView;

import ben.com.linkviewer.core.MVPView;

public interface MainView extends MVPView {

    ImageView getDisplay();

    void showProgress();

    void hideProgress();

    void requestPermissions();

    void finishView(int seconds);
}