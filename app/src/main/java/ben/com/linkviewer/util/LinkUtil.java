package ben.com.linkviewer.util;

import android.content.Intent;

import ben.com.linkviewer.core.App;
import ben.com.linkviewer.model.LinkModel;

public class LinkUtil {

    public static Intent getRequest(LinkModel model) {
        if (model != null) {
            Intent intent = new Intent(App.SHOW_LINK);
            intent.putExtra("id", model.getId());
            intent.putExtra("link", model.getLink());
            intent.putExtra("date", model.getDate());
            return intent;
        }

        return null;
    }

    public static LinkModel getResponse(Intent intent) {
        LinkModel model = new LinkModel();
        if (intent != null) {
            if (intent.getAction() != null && intent.getAction().equals(App.SHOW_LINK)) {

                model.setId(intent.getLongExtra("id", 0L));
                model.setLink(intent.getStringExtra("link"));
                model.setDate(intent.getStringExtra("date"));
                model.setStatus(intent.getIntExtra("status", 0));

                return model;
            }
        }

        return null;
    }
}
