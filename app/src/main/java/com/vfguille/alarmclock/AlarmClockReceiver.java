package com.vfguille.alarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Debug;
import android.util.Log;

public class AlarmClockReceiver extends BroadcastReceiver {
    private final String TAG = "AlarmClockReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        //RingtoneManager.(context, RingtoneManager.getDefaultUri())
        Log.d(TAG, "PATATAAAAAAAAAAAAAAA");
    }
}
