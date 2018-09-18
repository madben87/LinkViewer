package ben.com.linkviewer.ui.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import javax.inject.Inject;

import ben.com.linkviewer.R;
import ben.com.linkviewer.core.App;
import ben.com.linkviewer.model.LinkModel;
import ben.com.linkviewer.model.Status;
import ben.com.linkviewer.util.LinkUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    public static final String BASE_URL = "content://ben.com.linklauncher.linkprovider/link";

    @BindView(R.id.linkImage)
    ImageView linkImage;
    @BindView(R.id.date)
    TextView date;

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.getScreenInjector().inject(this);

        ButterKnife.bind(this);

        presenter.attachView(this);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getAction() != null && intent.getAction().equals(App.SHOW_LINK)) {

                presenter.needShowLink(LinkUtil.getResponse(intent));

            } else if (intent.getAction() != null && intent.getAction().equals(App.SHOW_HISTORY_LINK)) {
                presenter.needShowHistoryLink(LinkUtil.getResponse(intent));
            } else {
                new CountDownTimer(15000, 1000) {

                    @SuppressLint("SetTextI18n")
                    public void onTick(long millisUntilFinished) {
                        date.setText("App LinkViewer is not a stand-alone application and will be closed after " + millisUntilFinished / 1000 + " seconds");
                    }

                    public void onFinish() {
                        activityFinish();
                    }

                }.start();
            }
        }
    }

    @Override
    public void showMessage(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public ImageView getDisplay() {
        return linkImage;
    }

    private void activityFinish() {
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
