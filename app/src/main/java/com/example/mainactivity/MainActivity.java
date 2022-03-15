package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.mainactivity.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MyBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("TELA", "Tela 1");
        startService(intent);

        binding.btnIrTela2.setOnClickListener(view -> {
            Intent intent2 = new Intent(this, SecondActivity.class);
            startActivity(intent2);
        });

        List<MyContact> contacts = ContactsHelper.getContacts(this);

        for (MyContact contact : contacts) {
            Log.d("PHTS", "ID: " + contact.getId() + ", Name: " + contact.getName());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        receiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);

        registerReceiver(receiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("PHTS", "Status do Wi-Fi foi Alterado");
        }

    }
}