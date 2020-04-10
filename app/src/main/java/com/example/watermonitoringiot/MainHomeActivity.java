package com.example.watermonitoringiot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainHomeActivity extends AppCompatActivity {

    TextView txtTds,txtType,txtMode,txtTdsValue;
    SharedPreferences sharedPreferences;

    ProgressBar progressBar;
    Handler handler = new Handler();
    int tdsValue = 0;
    int cnt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        txtTds =findViewById(R.id.txtHomeTds);
        txtType =findViewById(R.id.txtHomeFilterType);
        txtMode =findViewById(R.id.txtHomeFilterMode);

        progressBar = findViewById(R.id.progressBar);
        txtTdsValue =findViewById(R.id.txtTdsValue);

        sharedPreferences=getSharedPreferences(Common.LOGIN_KEY,MODE_PRIVATE);
        int userId = sharedPreferences.getInt("user_id",0);

        if(userId!=0) {

            String url = Url.url + "?op=fetch_home&user_id="+userId;
            StringRequest sr = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject obj = new JSONObject(response);
                        if (obj.getInt("value")>0) {

                            cnt=0;
                            tdsValue = obj.getInt("tds");
                            txtTds.setText(tdsValue+" PPM");
                            txtTdsValue.setText(tdsValue+" TDS");

                            txtType.setText(obj.getString("filter_type"));
                            txtMode.setText(obj.getString("is_auto_mode"));

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    while (cnt <= tdsValue) {
                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                progressBar.setProgress(cnt);
                                            }
                                        });
                                        try {
                                            Thread.sleep(5);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        cnt++;
                                    }
                                }
                            }).start();

                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid User Name and Password", Toast.LENGTH_SHORT).show();
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
}
