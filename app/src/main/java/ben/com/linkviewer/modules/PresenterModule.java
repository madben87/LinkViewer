package ben.com.linkviewer.modules;

import ben.com.linkviewer.core.ScreenScope;
import ben.com.linkviewer.ui.main.MainPresenter;
import ben.com.linkviewer.ui.main.MainPresenterImpl;
import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @ScreenScope
    MainPresenter providesPresenter() {
        return new MainPresenterImpl();
    }
}
