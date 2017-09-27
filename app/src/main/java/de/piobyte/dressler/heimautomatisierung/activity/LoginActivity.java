package de.piobyte.dressler.heimautomatisierung.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.piobyte.dressler.heimautomatisierung.R;
import de.piobyte.dressler.heimautomatisierung.rest.HttpHandler;

public class LoginActivity extends AppCompatActivity{
    Button loginButton;
    EditText ipAdressText, passwordText;
    TextView loginText;
    private Boolean apiConnStat = false;
    private String password, ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().show();

        loginButton = (Button) findViewById(R.id.login_button);
        ipAdressText = (EditText) findViewById(R.id.ip_adress);
        passwordText = (EditText) findViewById(R.id.password);
        loginText = (TextView) findViewById(R.id.login_text);

        loginText.setText("Bitte geben Sie ihre Anmeldedaten ein.");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ip = "http://" + ipAdressText.getText().toString() + ":8123/api/";
                password = passwordText.getText().toString();
                new ApiLogin().execute();
            }
        });


    }
    private class ApiLogin extends AsyncTask<Void, Void, Void> {
        @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Toast.makeText(LoginActivity.this,"Verbindungstest zu HomeAssistant...",Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Void doInBackground(Void... arg0) {
                HttpHandler tryApiConn = new HttpHandler();
                String apiTestStr = tryApiConn.makeServiceCall(ip, password);
                Log.d("JSONSTRING", "" + apiTestStr);
                if(apiTestStr != null)
                    apiConnStat = tryApiConn.lookForApiConn(apiTestStr);
                else
                    apiConnStat = false;
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                if (!apiConnStat) {
                    Toast.makeText(getApplicationContext(), "Falsche Logindaten oder keine Netzwerkverbindung", Toast.LENGTH_LONG).show();
                    //apiConnStat = false;
                }else{
                    Toast.makeText(getApplicationContext(), "Verbindung erfolgreich! - Sie sind eingeloggt.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
    }
}

