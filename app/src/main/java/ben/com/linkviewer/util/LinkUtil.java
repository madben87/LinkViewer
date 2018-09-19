package ben.com.linkviewer.util;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

import ben.com.linkviewer.core.App;
import ben.com.linkviewer.model.LinkModel;

public class LinkUtil {

    public static final String ID = "id";
    public static final String LINK = "link";
    public static final String DATE = "date";
    public static final String STATUS = "status";
    public static final String IMAGE = "image";

    public static Intent createServiceIntent(LinkModel model, Context context, Bitmap bitmap, Class clazz) {
        Intent intent = new Intent(context, clazz);
        if (model != null) {
            intent.putExtra(ID, model.getId());
            intent.putExtra(LINK, model.getLink());
            intent.putExtra(DATE, model.getDate());
            intent.putExtra(STATUS, model.getStatus());
        }

        if (bitmap != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            intent.putExtra(IMAGE,byteArray);
        }

        return intent;
    }

    public static Intent getRequest(LinkModel model, String action) {
        if (model != null) {
            Intent intent = new Intent(action);
            intent.putExtra(ID, model.getId());
            intent.putExtra(LINK, model.getLink());
            intent.putExtra(DATE, model.getDate());
            intent.putExtra(STATUS, model.getStatus());
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            return intent;
        }

        return null;
    }

    public static LinkModel getResponse(Intent intent) {
        LinkModel model = new LinkModel();
        if (intent != null) {

            model.setId(intent.getLongExtra(ID, 0L));
            model.setLink(intent.getStringExtra(LINK));
            model.setDate(intent.getStringExtra(DATE));
            model.setStatus(intent.getIntExtra(STATUS, 0));

            return model;
        }

        return null;
    }

    public static ContentValues modelToContentValues(LinkModel model) {

        ContentValues cv = new ContentValues();

        cv.put(ID, model.getId());
        cv.put(LINK, model.getLink());
        cv.put(DATE, model.getDate());
        cv.put(STATUS, model.getStatus());

        return cv;
    }

    public static LinkModel modelFromContentValues(ContentValues cv) {

        LinkModel model = new LinkModel();

        model.setId(cv.getAsLong(ID));
        model.setLink(cv.getAsString(LINK));
        model.setDate(cv.getAsString(DATE));
        model.setStatus(cv.getAsInteger(ID));

        return model;
    }
}

/*ByteArrayOutputStream stream = new ByteArrayOutputStream();
bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
byte[] byteArray = stream.toByteArray();

Intent in1 = new Intent(this, Activity2.class);
in1.putExtra("image",byteArray);*/
