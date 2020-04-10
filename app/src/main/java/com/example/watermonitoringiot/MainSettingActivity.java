package com.example.watermonitoringiot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainSettingActivity extends AppCompatActivity {

    EditText edUserId,edUserName,edFilterType,edAutoTds;
    Switch swAutoMode;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setting);

        edUserId = findViewById(R.id.edSettingUserId);
        edUserName = findViewById(R.id.edSettingUserName);
        edFilterType = findViewById(R.id.edSettingFilterType);
        edAutoTds = findViewById(R.id.edSettingAutoTds);
        swAutoMode =findViewById(R.id.svSettingAutoMode);


        sharedPreferences = getSharedPreferences(Common.LOGIN_KEY, MODE_PRIVATE);
        String userName = sharedPreferences.getString("name","");
        int userId = sharedPreferences.getInt("user_id",0);

        edUserId.setText(userId+"");
        edUserName.setText(userName);


        String url = Url.url + "?op=get_setting&user_id="+userId;
        StringRequest sr = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getInt("value")>0)
                    {
                        JSONArray jsonArr =obj.getJSONArray("setting");
                        JSONObject jsonObject = jsonArr.getJSONObject(0);

                        edFilterType.setText(jsonObject.getString("filter_type"));
                        if(jsonObject.getString("is_auto_mode").equals("yes"))
                        {
                            swAutoMode.setChecked(true);
                        }
                        else
                        {
                            swAutoMode.setChecked(false);
                        }
                        edAutoTds.setText(jsonObject.getString("auto_mode_tds"));
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

    public void onClickSettingUpdate(View view) {

        String userId = edUserId.getText().toString();
        String filterType = edFilterType.getText().toString();
        String autoTds = edAutoTds.getText().toString();
        String autoMode="";
        if(swAutoMode.isChecked()) {
            autoMode="yes";
        }
        else
        {
            autoMode="no";
        }

        String url = Url.url+"?op=update_setting&user_id="+userId+"&auto_mode="+autoMode+"&filter_type="+filterType+"&auto_mode_tds="+autoTds;
        StringRequest sr = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getInt("value")>0)
                    {
                        Toast.makeText(MainSettingActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainSettingActivity.this, "Error in Update", Toast.LENGTH_SHORT).show();
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