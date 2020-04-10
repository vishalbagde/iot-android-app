package com.example.watermonitoringiot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainModeSwitchActivity extends AppCompatActivity {

    Switch swich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mode_switch);

        swich = findViewById(R.id.swSwitchMode);
        swich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Toast.makeText(MainModeSwitchActivity.this, "On", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainModeSwitchActivity.this, "Off", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
