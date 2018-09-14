package ben.com.linkviewer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LinkViewerReceiver extends BroadcastReceiver {

    public static String SHOW_LINK = "ben.com.linklauncher.show_link";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (intent.getAction() != null && intent.getAction().equals(SHOW_LINK)) {

                //LinkModel model = LinkUtil.getResponse(intent);
//
                //String s = model.getLink();

                intent.setClass(context, MainActivity.class);

                context.startActivity(intent);
            }
        }
    }
}
