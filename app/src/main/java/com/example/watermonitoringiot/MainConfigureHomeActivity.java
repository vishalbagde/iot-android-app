package com.example.watermonitoringiot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainConfigureHomeActivity extends AppCompatActivity {

    Button btnUrlSave;
    EditText edUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_configure_home);

        btnUrlSave=findViewById(R.id.btnUrlSave);
        edUrl=findViewById(R.id.edUrl);

        edUrl.setText(Url.url);

        btnUrlSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Url.url=edUrl.getText().toString();
                Toast.makeText(MainConfigureHomeActivity.this, "change Url successful", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
