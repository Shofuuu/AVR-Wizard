package com.example.avrwizard;

import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import android.app.Application;

public class JSONEngine extends Application {
    private String json;
    private String ic_name;
    private JSONObject data;
    private JSONArray efuse;

    private String loadJSONFromAsset() {
        String raw_json = null;

        try {
            InputStream is = getAssets().open("microchip_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return raw_json;
    }

    private void loadDataIC(){
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            data = obj.getJSONObject(getICName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setICName(String name){
        ic_name = name;
        loadDataIC();
    }

    public String getICName(){
        return ic_name;
    }

    public String getFlash() {
       String tmp = "";
        try {
            tmp = data.getString("flash");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public String getSram(){
        String tmp = "";
        try {
            tmp = data.getString("sram");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public String getEeprom(){
        String tmp = "";
        try {
            tmp = data.getString("eeprom");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public String getEFuseName(int index){
        try {
            JSONObject fuse_e = data.getJSONObject("efuse");
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

