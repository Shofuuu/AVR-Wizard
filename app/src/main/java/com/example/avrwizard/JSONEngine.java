package com.example.avrwizard;

import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;

public class JSONEngine extends AppCompatActivity {

    private String json;
    JSONArray data;
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
            for (int i = 0; i<data.length(); i++){
                JSONObject data_in = data.getJSONObject(i);
                String flash = (String) data_in.get("flash");
                String eeprom = (String) data_in.get("eeprom");
                String sram = (String) data_in.get("sram");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}

