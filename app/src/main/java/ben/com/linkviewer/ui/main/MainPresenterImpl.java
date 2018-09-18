package ben.com.linkviewer.ui.main;

import android.content.Context;
import android.net.Uri;

import com.nostra13.universalimageloader.core.ImageLoader;

import javax.inject.Inject;

import ben.com.linkviewer.core.App;
import ben.com.linkviewer.model.LinkModel;
import ben.com.linkviewer.model.Status;
import ben.com.linkviewer.service.LinkShowService;
import ben.com.linkviewer.util.LinkUtil;

public class MainPresenterImpl implements MainPresenter<MainView> {

    @Inject
    Context context;

    private MainView view;

    public MainPresenterImpl() {
        App.getAppInjector().inject(this);
    }

    @Override
    public void attachView(MainView mainView) {
        this.view = mainView;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void needShowLink(LinkModel model) {
        view.getDisplay().setImageResource(0);
        try {
            ImageLoader.getInstance().displayImage(model.getLink(), view.getDisplay());
            model.setStatus(Status.ACTIVE.getValue());
            context.getContentResolver().insert(Uri.parse(App.BASE_URL), LinkUtil.modelToContentValues(model));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void needShowHistoryLink(LinkModel model) {
        view.getDisplay().setImageResource(0);
        if (model.getStatus() == Status.ACTIVE.getValue()) {
            try {
                ImageLoader.getInstance().displayImage(model.getLink(), view.getDisplay());
                context.startService(LinkUtil.serviceIntent(model, context, LinkShowService.class));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                ImageLoader.getInstance().displayImage(model.getLink(), view.getDisplay());
                model.setStatus(Status.ACTIVE.getValue());
                context.getContentResolver().insert(Uri.parse(App.BASE_URL), LinkUtil.modelToContentValues(model));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
