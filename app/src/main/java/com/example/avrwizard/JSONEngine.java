package com.example.avrwizard;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class JSONEngine extends AppCompatActivity {
    String json;
    String IC;
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


    public Object setIC (String IC){
        loadJSONFromAsset();
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray data = obj.getJSONArray(IC);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.IC = "IC";
    }
     public String getIC (){
        return this.IC;
     }


    }
}
