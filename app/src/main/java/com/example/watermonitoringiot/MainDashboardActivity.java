package com.example.watermonitoringiot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainDashboardActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView txtUserName,txtUserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        txtUserName = findViewById(R.id.txtdashboardTitle);
        txtUserId =findViewById(R.id.txtDashboardUserId);

        sharedPreferences = getSharedPreferences(Common.LOGIN_KEY, MODE_PRIVATE);
        String userName = sharedPreferences.getString("name","");
        int userId = sharedPreferences.getInt("user_id",0);

        if(userId==0)
        {
            Intent intent = new Intent(MainDashboardActivity.this, MainLoginActivity.class);
            startActivity(intent);
            finish();
        }

        txtUserName.setText("Welcome "+userName);
        txtUserId.setText("User ID "+userId);

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

    public void onClickDashboardHome(View view) {
        Intent intent = new Intent(MainDashboardActivity.this, MainHomeActivity.class);
        startActivity(intent);
    }

    public void onClickDashboardOperation(View view) {
        Intent intent = new Intent(MainDashboardActivity.this, MainOperationActivity.class);
        startActivity(intent);
    }

    public void onClickDashboardSetting(View view) {
        Intent intent = new Intent(MainDashboardActivity.this, MainSettingActivity.class);
        startActivity(intent);
    }
}
