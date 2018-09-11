package ben.com.linkviewer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import ben.com.linkviewer.model.LinkModel;

public class MainActivity extends AppCompatActivity {

    public static String SHOW_LINK = "ben.com.linklauncher.show_link";

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
                LinkModel model = intent.getParcelableExtra("model");

                imageView.getSettings().setJavaScriptEnabled(true);
                imageView.loadUrl(model.getLink());

                date.setText(model.getDate());
            }
        }
    }
}
