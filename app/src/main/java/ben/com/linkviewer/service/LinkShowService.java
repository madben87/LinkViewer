package ben.com.linkviewer.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import ben.com.linkviewer.core.App;
import ben.com.linkviewer.model.LinkModel;
import ben.com.linkviewer.util.LinkUtil;

public class LinkShowService extends Service {

    private static final String MY_DIR = "/BIGDIG/test/LinkViewer";
    private static final String MY_IMAGE_NAME = "image.png";

    private Context context;

    public LinkShowService() {
        this.context = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null) {

            final LinkModel model = LinkUtil.getResponse(intent);

            saveImage(intent.getByteArrayExtra(LinkUtil.IMAGE));

            if (model != null) {

                new CountDownTimer(15000, 1000) {

                    @SuppressLint("SetTextI18n")
                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {
                        getContentResolver().delete(Uri.parse(App.BASE_URL + "/" + model.getId()), null, null);
                        Toast.makeText(context, "Item is deleted", Toast.LENGTH_SHORT).show();
                        stopSelf();
                    }

                }.start();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void saveImage(byte[] bytes) {

        String path = Environment.getExternalStorageDirectory().toString();

        if (new File(path + MY_DIR).mkdirs()) {
            File image = new File(path + MY_DIR + "/" + MY_IMAGE_NAME);

            FileOutputStream outStream;
            try {
                outStream = new FileOutputStream(image);
                outStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
