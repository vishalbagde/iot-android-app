package com.example.watermonitoringiot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainOperationActivity extends AppCompatActivity {

    Switch swPower,swAutoMode;
    String power,autoMode;
    int userId;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_operation);

        swPower = findViewById(R.id.swPower);
        swAutoMode=findViewById(R.id.swModeAuto);

        sharedPreferences = getSharedPreferences(Common.LOGIN_KEY, MODE_PRIVATE);
        String userName = sharedPreferences.getString("name","");
        userId = sharedPreferences.getInt("user_id",0);

        if(swPower.isChecked())
            power="on";
        else
            power="off";

        if(swAutoMode.isChecked())
            autoMode="yes";
        else
            autoMode="no";

        swPower.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    power="on";
                else
                    power="off";
                updateData(power,autoMode);
            }
        });

        swAutoMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    autoMode="yes";
                else
                    autoMode="no";
                updateData(power,autoMode);
            }
        });
    }

    public void updateData(String power,String autoMode)
    {
        String url = Url.url + "?op=update_operation&auto_mode="+autoMode+"&power="+power+"&user_id="+userId;
        Log.d("url",url);
        StringRequest sr = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getInt("value")>0)
                    {
                        Toast.makeText(MainOperationActivity.this, "Update Successful", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainOperationActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.fillInStackTrace().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(sr);

    }
}
