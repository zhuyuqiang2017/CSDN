package com.example.broadcastsender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mSendStaticBroadcast,mSendDynamicBroadcast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSendStaticBroadcast = (Button)findViewById(R.id.send_static_broadcast);
        mSendDynamicBroadcast = (Button)findViewById(R.id.send_dynamic_broadcast);
        mSendStaticBroadcast.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    sendBroadcast(new Intent("com.broadcast.static.test"));
            }
        });
        mSendDynamicBroadcast.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                sendBroadcast(new Intent("com.broadcast.dynamic.test"));
                sendBroadcast(new Intent("com.broadcast.dynamic.test"));
            }
        });
    }
}
