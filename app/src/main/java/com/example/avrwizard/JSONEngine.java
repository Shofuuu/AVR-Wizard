package com.example.avrwizard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.util.ArrayList;

import android.app.Application;

import kotlin.contracts.Returns;

public class JSONEngine extends Application {
//    private String json;
    private String ic_name;
    private JSONObject data;
    private JSONArray efuse;

    private String loadJSONFromAsset() {
        String raw_json = null;
        try {
            InputStream inputStream = getAssets().open("microchip_data.json");
            int size_stream = inputStream.available();
            byte[] buff_stream = new byte[size_stream];
            inputStream.read(buff_stream);
            raw_json = new String(buff_stream);
        } catch (IOException ex) {
            ex.printStackTrace();
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
        String tmp = null;
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
        String EFuseName = "";
        try {
            JSONObject fuse_e = data.getJSONObject("efuse");
            efuse = fuse_e.getJSONArray(String.valueOf(index));
            EFuseName = efuse.getString(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return EFuseName;
    }

    public String getEFuseValue(int index){
        String EFuseValue = "";
        try {
            JSONObject fuse_e = data.getJSONObject("efuse");
            efuse = fuse_e.getJSONArray(String.valueOf(index));
            EFuseValue = efuse.getString(2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return EFuseValue;
    }
}