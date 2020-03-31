package com.example.watermonitoringiot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainLoginActivity extends AppCompatActivity {

    EditText edUsername,edPassoword;
    TextView txtUsername,txtPassword;
    Button btnLogin;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        

        edUsername =findViewById(R.id.edUsername);
        edPassoword=findViewById(R.id.edPassword);

        txtUsername = findViewById(R.id.txtUserName);
        txtPassword=findViewById(R.id.txtPassword);

        btnLogin=findViewById(R.id.btnLogin);

        sharedPreferences=getSharedPreferences("loginDetails",MODE_PRIVATE);

        int user_id = sharedPreferences.getInt("user_id",0);
        if(user_id>0)
        {
            Intent intent = new Intent(MainLoginActivity.this,MainHomeActivity.class);
            startActivity(intent);
        }
    }

    public void onClickLogin(View view)
    {
        String username = edUsername.getText().toString();
        String password = edPassoword.getText().toString();

        if (username != null && password != null) {
            String url = Url.url + "/seminar/index.php?op=login&username=" + username + "+&password=" + password;
            StringRequest sr = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject obj = new JSONObject(response);
                        if (obj.getBoolean("succ")) {
                            Toast.makeText(getApplicationContext(), "login successful", Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor editor =sharedPreferences.edit();
                            editor.putInt("user_id",obj.getInt("user_id"));
                            editor.putString("name",obj.getString("name"));
                            editor.commit();

                            Intent intent = new Intent(MainLoginActivity.this,MainTdsGraphActivity.class);
                            startActivity(intent);

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
            RequestQueue rq = Volley.newRequestQueue(MainLoginActivity.this);
            rq.add(sr);
        }//end if
    }

}
