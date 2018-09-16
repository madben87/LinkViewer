package ben.com.linkviewer.core;

import javax.inject.Singleton;

import ben.com.linkviewer.modules.ContextModule;
import ben.com.linkviewer.ui.main.MainPresenterImpl;
import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class})
public interface AppInjector {

    void inject(MainPresenterImpl presenter);
}
