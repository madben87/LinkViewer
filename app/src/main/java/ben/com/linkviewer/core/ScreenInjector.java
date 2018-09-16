package ben.com.linkviewer.core;

import ben.com.linkviewer.modules.PresenterModule;
import ben.com.linkviewer.ui.main.MainActivity;
import dagger.Component;

@ScreenScope
@Component(modules = {PresenterModule.class})
public interface ScreenInjector {

    void inject(MainActivity activity);
}
