package ben.com.linkviewer.core;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import ben.com.linkviewer.modules.ContextModule;
import ben.com.linkviewer.modules.PresenterModule;
import ben.com.linkviewer.modules.RepositoryModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    public static final String BASE_URL = "content://ben.com.linklauncher.linkprovider/link";
    public static final String SHOW_LINK = "ben.com.linklauncher.show_link";
    public static final String SHOW_HISTORY_LINK = "ben.com.linklauncher.show_history_link";
    public static final String SHOW_LINK_SUCCESS = "ben.com.linklauncher.show_link_success";
    public static final String KEY_LINK = "link";

    private static App appInstance;
    private AppInjector appInjector;
    private ScreenInjector screenInjector;

    public static App getAppInstance() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder().name("links.realm").build();

        Realm.setDefaultConfiguration(config);

        appInstance = this;

        appInjector = DaggerAppInjector
                .builder()
                .contextModule(new ContextModule(this))
                .build();

        screenInjector = DaggerScreenInjector
                .builder()
                .presenterModule(new PresenterModule())
                .build();

        initImageLoader(this);
    }

    public static AppInjector getAppInjector() {
        return getAppInstance().appInjector;
    }

    public static ScreenInjector getScreenInjector() {
        return getAppInstance().screenInjector;
    }

    public static void initImageLoader(Context context) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.defaultDisplayImageOptions (options);
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        ImageLoader.getInstance().init(config.build());
    }
}
