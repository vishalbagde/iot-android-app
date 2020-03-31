package com.example.watermonitoringiot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainDashboardActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);
        sharedPreferences = getSharedPreferences(Common.LOGIN_KEY, MODE_PRIVATE);
    }

    public void onClickDashboardLogout(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("user_id", 0);
        editor.commit();
        Intent intent = new Intent(MainDashboardActivity.this, MainLoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void onClickDashboardSwitchMode(View view) {
        Intent intent = new Intent(MainDashboardActivity.this, MainModeSwitchActivity.class);
        startActivity(intent);
    }

}
