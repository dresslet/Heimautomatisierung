package de.piobyte.dressler.heimautomatisierung.rest;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class RestInterfaceTask extends AsyncTask<String, Void, String> {

    protected String doInBackground(String... url){
        URL endPoint;
        String result = null;
        try {
            endPoint = new URL("http://10.2.10.102:8123/api/states/sensor.fibaro_system_fgwpef_wall_plug_gen5_power_management");
            HttpURLConnection httpclient = (HttpURLConnection) endPoint.openConnection();
            httpclient.setRequestProperty("x-ha-access", "PASSWORD");
            httpclient.setRequestMethod("GET");
            httpclient.setRequestProperty("Content-Type", "application/json");
            //if(httpclient.getResponseCode() == 200){
                InputStream responseStream = httpclient.getInputStream();
                InputStreamReader responseStreamReader = new InputStreamReader(responseStream, "UTF-8");
                JsonReader jsonReader = new JsonReader(responseStreamReader);
                result = convertStreamToString(responseStream);
            Log.d("REST-TEST: ",  "" + result);
            //}
        }catch (Exception e){
            Log.e("ERROR: ", e.toString());
        }
        return result;
    }
    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
