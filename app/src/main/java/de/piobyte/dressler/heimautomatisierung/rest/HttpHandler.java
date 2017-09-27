package de.piobyte.dressler.heimautomatisierung.rest;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.piobyte.dressler.heimautomatisierung.model.hADevice;

public class HttpHandler {
    private String HOMEASSISTANTURL = "http://10.2.10.102:8123/";


    public HttpHandler() {
    }

    public String makeServiceCall(String reqUrl, String password) {
        String response = null;
        try {
            URL url = new URL(reqUrl); //"http://swapi.co/api/people/?page=");  //HOMEASSISTANTURL + reqUrl + "?api_password=PASSWORD");
            HttpURLConnection httpclient = (HttpURLConnection) url.openConnection();
            httpclient.setRequestProperty("x-ha-access", password);
            httpclient.setRequestMethod("GET");
            httpclient.setRequestProperty("Content-Type", "application/json");

            InputStream in = new BufferedInputStream(httpclient.getInputStream());
            response = convertStreamToJson(in);

        } catch (MalformedURLException e) {
            Log.e("REST", "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e("REST", "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e("REST", "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e("REST", "Exception: " + e.getMessage());
        }
        return response;
    }

    private String convertStreamToJson(InputStream is) {
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

    public Boolean lookForApiConn(String jsonStr){
        Boolean erg = false;
        ArrayList<hADevice> deviceList = new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);

            String apiConn = jsonObj.getString("message");
            if(apiConn.equals("API running.")){
                erg = true;
            }
        } catch (final JSONException e) {
            Log.e("JSON", "Json parsing error: " + e.getMessage());
        }
        return erg;
    }

    public ArrayList<hADevice> getDevicesfromJson(String jsonStr){

        ArrayList<hADevice> deviceList = new ArrayList<>();
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                JSONArray devices = jsonObj.getJSONArray("results");

                // looping through All Contacts
                for (int i = 0; i < devices.length(); i++) {
                    hADevice newDevice = new hADevice();
                    JSONObject c = devices.getJSONObject(i);

                    String name = c.getString("name");
                    String email = c.getString("homeworld");
                    String gender = c.getString("gender");

                    HttpHandler test = new HttpHandler();
                    String ur2l = c.getString("homeworld");
                    String jsonStrs = test.makeServiceCall("test", "test");
                    JSONObject jsonObj2 = new JSONObject(jsonStrs);
                    String namehw = jsonObj2.getString("name");

                    deviceList.add(newDevice);
                }
            } catch (final JSONException e) {
                Log.e("JSON", "Json parsing error: " + e.getMessage());
            }
        return deviceList;
    }
}