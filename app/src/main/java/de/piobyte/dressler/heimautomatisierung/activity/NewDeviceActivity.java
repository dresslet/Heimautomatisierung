package de.piobyte.dressler.heimautomatisierung.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import de.piobyte.dressler.heimautomatisierung.R;

public class NewDeviceActivity extends AppCompatActivity{

    //EditText ipAdressText, passwordText;
    ProgressBar inclusionProgressBar;
    TextView newDeviceText, progressBarText;
    CountDownTimer timerProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_device);
        getSupportActionBar().setTitle("Neues Gerät anlegen");

        inclusionProgressBar = (ProgressBar)findViewById(R.id.inclusion_bar);
        inclusionProgressBar.setMax(150);
        progressBarText = (TextView) findViewById(R.id.progress_text);
        newDeviceText = (TextView)findViewById(R.id.new_device_text);

        newDeviceText.setText("Hier können neue Geräte im Z-Wave Netz angemeldet werden. Zum Starten des Anmeldemodus bitte berühren.");


        newDeviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerProgressBar = new CountDownTimer(5000, 1000){
                    public void onTick(long millisUntilFinished)
                    {
                        newDeviceText.setVisibility(View.GONE);
                        inclusionProgressBar.setVisibility(View.VISIBLE);
                        progressBarText.setText("Suche nach neuen Geräten läuft...");
                        progressBarText.setVisibility(View.VISIBLE);
                    }
                    public void onFinish()
                    {
                        inclusionProgressBar.setVisibility(View.GONE);
                        progressBarText.setText("Es konnten keine neuen Geräte gefunden werden");
                    }
                }.start();



                if(inclusionProgressBar.getProgress() == inclusionProgressBar.getMax()){
                    inclusionProgressBar.setVisibility(View.GONE);
                }
                else{
                    inclusionProgressBar.setVisibility(View.VISIBLE);
                    inclusionProgressBar.setProgress(150);
                }
            }
        });

    }

}
