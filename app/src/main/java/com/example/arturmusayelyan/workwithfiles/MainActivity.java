package com.example.arturmusayelyan.workwithfiles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button sendIntentBtn;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendIntentBtn = findViewById(R.id.send_intent_btn);
        sendIntentBtn.setOnClickListener(this);
        IntentFilter intentFilter = new IntentFilter("test_broadcast");
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, intent.getStringExtra("data1"), Toast.LENGTH_SHORT).show();
            }
        };
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_intent_btn:
                someFunction1();
                break;
        }
    }

    private void someFunction1() {
        final String TEST_BR = "test_broadcast";
        Intent intent = new Intent(TEST_BR);
        intent.putExtra("data1", "Artur");
        intent.putExtra("data2", "Musayelyan");
        sendBroadcast(intent);
    }

    private void someFunction2() {
        final String TEST_BR = "test_broadcast";
        String reqiredPermission = "test_myPermission";
        Intent intent = new Intent(TEST_BR);
        intent.putExtra("data1", "Artur");
        intent.putExtra("data2", "Musayelyan");
        sendOrderedBroadcast(intent, reqiredPermission);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}
