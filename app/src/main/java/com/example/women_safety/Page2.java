package com.example.women_safety;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Page2 extends AppCompatActivity {
    Button signbutton;
    EditText signconfirmpass, signpassword1, signemail, signusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_pageup);

        // Initialize UI elements

        signbutton = findViewById(R.id.signlogin_button);
        signemail = findViewById(R.id.signemail_input);
        signpassword1 = findViewById(R.id.signpass);
        signconfirmpass = findViewById(R.id.signconfirmpass);
        signusername = findViewById(R.id.signusername_input);

        signbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the entered values
                String username = signusername.getText().toString();
                String email = signemail.getText().toString();
                String password = signpassword1.getText().toString();
                String confirmpass = signconfirmpass.getText().toString();


                DatabaseHelper dbHelper = new DatabaseHelper(Page2.this);
                // Open database for writing

                // Check if passwords match
                if (password.equals(confirmpass)) {
                    // Insert user data into the database
                    boolean isInserted = dbHelper.insertUserData(username, email, password);

                    if (isInserted) {
                        Toast.makeText(Page2.this, "Data entered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Page2.this, Dashboard.class));
                        //finish();
                    } else {
                        Toast.makeText(Page2.this, "Data insertion failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Password mismatch
                    Toast.makeText(Page2.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


