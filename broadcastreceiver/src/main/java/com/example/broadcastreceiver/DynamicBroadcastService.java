package com.example.broadcastreceiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2017/2/26 0026.
 */

public class DynamicBroadcastService extends Service {
    private DynamicBroadcast mDynamic;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mDynamic = new DynamicBroadcast();
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction("com.broadcast.dynamic.test");
//        registerReceiver(mDynamic,mFilter);
        registerReceiver(mDynamic,mFilter,"com.permission.test",new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.i("zhuyuqiang","handler");
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mDynamic);
    }
    class DynamicBroadcast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("zhuyuqiang","dynamic broadcast action = "+intent.getAction());
//            DynamicBroadcastService.this.stopSelf();
        }
    }
}
