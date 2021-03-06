package imeng.tdemos_robolectric.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import imeng.tdemos_robolectric.service.SampleIntentService;

public class AlarmManagerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentService = new Intent(context, SampleIntentService.class);
        context.startService(intentService);
    }
}
