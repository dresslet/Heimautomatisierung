package de.piobyte.dressler.heimautomatisierung.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.piobyte.dressler.heimautomatisierung.R;

/**
 * Created by Piobyte-Gast on 20.09.2017.
 */

public class LoginActivity extends AppCompatActivity{
    Button loginButton;
    EditText ipAdressText, passwordText;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().show();

        loginButton = (Button)findViewById(R.id.login_button);
        ipAdressText = (EditText)findViewById(R.id.ip_adress);
        passwordText = (EditText)findViewById(R.id.password);
        loginText = (TextView)findViewById(R.id.login_text);

        loginText.setText("Bitte geben Sie ihre Anmeldedaten ein.");


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ipAdressText.getText().toString().equals("1.") &&
                        passwordText.getText().toString().equals("admin")) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

