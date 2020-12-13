package com.example.avrwizard;

import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;

public class JSONEngine extends AppCompatActivity {

    private String json;
    private String ic_name;
    JSONArray data;
    JSONArray efuse;
    public String loadJSONFromAsset() {
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
        return null;
    }


    public void setIC (String IC){
        loadJSONFromAsset();
        try {
            JSONObject obj = new JSONObject(json);
            data = obj.getJSONArray(IC);
            ic_name = IC;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public String getIc_name(){
        setIC(ic_name);
        return ic_name;
    }
    public String getFlash(){
        try {
            setIC(data.getString(0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getFlash();
    }
    public String getSram(){
        try {
            setIC(data.getString(1));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getSram();
    }
    public String getEeprom(){
        try {
            setIC(data.getString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getEeprom();
    }

    public String getEFuse(){
        try {
            JSONObject fuse_e = data.getJSONObject(3);
            for (int i = 0; i<3 ; i++){
                efuse.put(fuse_e.getString(String.valueOf(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getEFuse();
    }
}

