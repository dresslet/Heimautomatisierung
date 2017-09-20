package de.piobyte.dressler.heimautomatisierung.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.piobyte.dressler.heimautomatisierung.R;

/**
 * Created by Piobyte-Gast on 20.09.2017.
 */

public class LoginActivity extends Activity{
    Button loginButton;
    EditText ipAdressText, passwordText;
    TextView ipAdressLabel, passwordLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button)findViewById(R.id.login_button);
        ipAdressText = (EditText)findViewById(R.id.ip_adress);
        passwordText = (EditText)findViewById(R.id.password);
        ipAdressLabel = findViewById(R.id.ip_adress_label);
        passwordLabel = findViewById(R.id.password_label);

        ipAdressLabel.setText("IP-Adresse: ");
        passwordLabel.setText("Passwort: ");


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ipAdressText.getText().toString().equals("admin") &&
                        passwordText.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

