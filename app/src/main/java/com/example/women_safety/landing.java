package com.example.women_safety;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class landing extends AppCompatActivity {

    Button login,sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing);

        login=(Button)findViewById(R.id.landinglogin_button);
        sign=(Button)findViewById(R.id.landingSign_up_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(landing.this, MainActivity.class));
                //finish();
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(landing.this, Page2.class));
               // finish();
            }
        });
    }
}
