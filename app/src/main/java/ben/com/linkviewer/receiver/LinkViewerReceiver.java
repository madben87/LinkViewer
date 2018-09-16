package ben.com.linkviewer.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import ben.com.linkviewer.core.App;
import ben.com.linkviewer.ui.main.MainActivity;

public class LinkViewerReceiver extends BroadcastReceiver {

    //public static String SHOW_LINK = "ben.com.linklauncher.show_link";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (intent.getAction() != null && (intent.getAction().equals(App.SHOW_LINK) || intent.getAction().equals(App.SHOW_HISTORY_LINK))) {

                intent.setClass(context, MainActivity.class);

                context.startActivity(intent);
            }
        }
    }
}
