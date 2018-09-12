package ben.com.linkviewer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import ben.com.linkviewer.model.LinkModel;
import ben.com.linkviewer.util.LinkUtil;

public class MainActivity extends AppCompatActivity {

    public static String SHOW_LINK = "ben.com.linklauncher.show_link";
    public static String SHOW_LINK_SUCCESS = "ben.com.linklauncher.show_link_success";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView imageView = findViewById(R.id.img);
        TextView date = findViewById(R.id.date);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getAction() != null && intent.getAction().equals(SHOW_LINK)) {

                //String link =  intent.getCharSequenceExtra("link").toString();

                //Uri uri = Uri.parse(link);

                LinkModel model = LinkUtil.getResponse(intent);

                imageView.getSettings().setJavaScriptEnabled(true);
                if (model != null) {
                    imageView.loadUrl(model.getLink());
                    date.setText(model.getDate());
                }

                Intent response = new Intent(SHOW_LINK_SUCCESS);
                response.putExtra("status", 1);
                response.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

                sendBroadcast(response);
            }
        }
    }
}
