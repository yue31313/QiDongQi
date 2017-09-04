package org.kotei.launcher2;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class HDMIService extends Service{

    private HdmiReceiver mHdmiReceiver;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        mHdmiReceiver = new HdmiReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.HDMI_PLUGGED");
        filter.addAction("android.hardware.usb.action.USB_STATE");
        registerReceiver(mHdmiReceiver, filter);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        return super.onStartCommand(intent, flags, startId);
    }

    private class HdmiReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            if (intent.getAction().equals("android.intent.action.HDMI_PLUGGED")) {
                boolean flag = intent.getBooleanExtra("state", false);
                if (flag == true) {
                    Intent mIntent = new Intent();
                    mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ComponentName comp = new ComponentName("org.kotei.launcher2", "org.kotei.launcher2.Launcher");
                    mIntent.setComponent(comp);
                    mIntent.setAction("android.intent.action.VIEW");
                    startActivity(mIntent);
                }else{
                    LauncherApplication appKiller = (LauncherApplication) getApplication();
                    appKiller.killApp();
                }
            }
//            if (intent.getAction().equals("android.hardware.usb.action.USB_STATE")) {
//                boolean connected = intent.getExtras().getBoolean("connected");
//                if (connected == true) {
//                    Intent mIntent = new Intent( );
//                    mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    ComponentName comp = new ComponentName("org.kotei.launcher2", "org.kotei.launcher2.Launcher");
//                    mIntent.setComponent(comp);
//                    mIntent.setAction("android.intent.action.VIEW");
//                    startActivity(mIntent);
//                } else {
//                }
//            }
        }
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver(mHdmiReceiver);
    }
}
