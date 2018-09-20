package ben.com.linkviewer.ui.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import javax.inject.Inject;

import ben.com.linkviewer.R;
import ben.com.linkviewer.core.App;
import ben.com.linkviewer.model.LinkModel;
import ben.com.linkviewer.model.Status;
import ben.com.linkviewer.service.LinkShowService;
import ben.com.linkviewer.util.DateUtil;
import ben.com.linkviewer.util.LinkUtil;

public class MainPresenterImpl implements MainPresenter<MainView> {

    @Inject
    Context context;

    private MainView mainView;
    private LinkModel linkModel;

    public MainPresenterImpl() {
        App.getAppInjector().inject(this);
    }

    @Override
    public void attachView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void detachView() {
        mainView = null;
    }

    @Override
    public void needShowLink(LinkModel model) {
        linkModel = model;
        mainView.getDisplay().setImageResource(0);
        try {

            ImageLoader.getInstance().displayImage(model.getLink(), mainView.getDisplay(), new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    if (mainView != null || view != null) {
                        mainView.showProgress();
                    }
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    linkModel.setStatus(Status.ERROR.getValue());
                    linkModel.setDate(DateUtil.getCurrentDate());
                    linkModel.setId(linkModel.hashCode());
                    context.getContentResolver().insert(Uri.parse(App.BASE_URL), LinkUtil.modelToContentValues(linkModel));
                    mainView.hideProgress();
                    mainView.showMessage("Download ERROR");
                    mainView.finishView(15);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    if (mainView != null || view != null) {
                        mainView.hideProgress();
                        linkModel.setStatus(Status.ACTIVE.getValue());
                        linkModel.setDate(DateUtil.getCurrentDate());
                        linkModel.setId(linkModel.hashCode());
                        ((ImageView) view).setImageBitmap(loadedImage);
                        context.getContentResolver().insert(Uri.parse(App.BASE_URL), LinkUtil.modelToContentValues(linkModel));
                    }
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void needShowHistoryLink(LinkModel model) {
        linkModel = model;
        mainView.getDisplay().setImageResource(R.drawable.logo);

        if (model.getStatus() == Status.ACTIVE.getValue()) {

            try {

                ImageLoader.getInstance().displayImage(model.getLink(), mainView.getDisplay(), new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        if (mainView != null || view != null) {
                            mainView.showProgress();
                        }
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        linkModel.setStatus(Status.ERROR.getValue());
                        linkModel.setDate(DateUtil.getCurrentDate());
                        linkModel.setId(linkModel.hashCode());
                        context.getContentResolver().insert(Uri.parse(App.BASE_URL), LinkUtil.modelToContentValues(linkModel));
                        mainView.hideProgress();
                        mainView.showMessage("Download ERROR");
                        mainView.finishView(15);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        if (mainView != null || view != null) {
                            mainView.hideProgress();
                            mainView.requestPermissions();
                            ((ImageView) view).setImageBitmap(loadedImage);
                            context.startService(LinkUtil.createServiceIntent(linkModel, context, loadedImage, LinkShowService.class));
                        }
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {

                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {

            try {

                ImageLoader.getInstance().displayImage(model.getLink(), mainView.getDisplay(), new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        if (mainView != null || view != null) {
                            mainView.showProgress();
                        }
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        linkModel.setStatus(Status.ERROR.getValue());
                        linkModel.setDate(DateUtil.getCurrentDate());
                        linkModel.setId(linkModel.hashCode());
                        context.getContentResolver().insert(Uri.parse(App.BASE_URL), LinkUtil.modelToContentValues(linkModel));
                        mainView.hideProgress();
                        mainView.showMessage("Download ERROR");
                        mainView.finishView(15);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        if (mainView != null || view != null) {
                            mainView.hideProgress();
                            linkModel.setStatus(Status.ACTIVE.getValue());
                            ((ImageView) view).setImageBitmap(loadedImage);
                            context.getContentResolver().insert(Uri.parse(App.BASE_URL), LinkUtil.modelToContentValues(linkModel));
                        }
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {

                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
