package com.example.avrwizard;

import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import android.app.Application;

public class JSONEngine extends Application {
    private String ic_name;
    JSONArray data;
    JSONArray efuse;
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("microchip_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }


    public void setIC (String IC){
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            data = obj.getJSONArray(IC);
            ic_name = IC;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public String getIc_name(){
        return ic_name;
    }
    public String getFlash() {
       String tmp = "";
        try {
            tmp = data.getString(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tmp;
    }
    public String getSram(){
        String tmp = "";
        try {
            tmp = data.getString(1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tmp;
    }
    public String getEeprom(){
        String tmp = "";
        try {
            tmp = data.getString(2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public String getEFuseName(int index){
        try {
            JSONObject fuse_e = data.getJSONObject(3);
            efuse = fuse_e.getJSONArray(String.valueOf(index));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return String.valueOf(efuse);
    }

    public String getEFuseValue(int index){
       String value = "";
       try {
           value = efuse.getString(index);
       } catch (JSONException e) {
           e.printStackTrace();
       }
       return value;
    }
}

