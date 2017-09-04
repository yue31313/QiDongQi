package org.kotei.launcher2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class HDMIReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Intent i = new Intent("com.android.launcher.HDMIService");
            context.startService(i);
        }
    }
}