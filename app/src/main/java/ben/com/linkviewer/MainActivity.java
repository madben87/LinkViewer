package ben.com.linkviewer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import ben.com.linkviewer.model.LinkModel;
import ben.com.linkviewer.util.LinkUtil;

public class MainActivity extends AppCompatActivity {

    public static String SHOW_LINK = "ben.com.linklauncher.show_link";
    public static String SHOW_LINK_SUCCESS = "ben.com.linklauncher.show_link_success";

    public static final String BASE_URL = "content://ben.com.linklauncher.linkprovider/link";

    private TextView date;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView imageView = findViewById(R.id.img);
        date = findViewById(R.id.date);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            if (intent != null) {
                if (intent.getAction() != null && intent.getAction().equals(SHOW_LINK)) {

                    LinkModel model = LinkUtil.getResponse(intent);

                    imageView.getSettings().setJavaScriptEnabled(true);
                    if (model != null) {
                        imageView.loadUrl(model.getLink());
                        date.setText(model.getDate());

                        Uri newUri = getContentResolver().insert(Uri.parse(BASE_URL), LinkUtil.modelToContentValues(model));
                    }

                    //Intent response = new Intent(SHOW_LINK_SUCCESS);
                    //response.putExtra("status", 1);
                    //response.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
//
                    //sendBroadcast(response);
                }
            }
        }else {
            /*Toast.makeText(this, "App LinkViewer is not a stand-alone application and will be closed after" + n + "seconds", Toast.LENGTH_SHORT).show();
            onDestroy();*/

            new CountDownTimer(15000, 1000) {

                @SuppressLint("SetTextI18n")
                public void onTick(long millisUntilFinished) {
                    date.setText("App LinkViewer is not a stand-alone application and will be closed after " + millisUntilFinished / 1000 + " seconds");
                }

                public void onFinish() {
                    onDestroy();
                }

            }.start();
        }
    }
}
