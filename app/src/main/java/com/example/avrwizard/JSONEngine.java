package com.example.avrwizard;

import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JSONEngine extends AppCompatActivity {
    public ArrayList <GlobalClass> loadJSONFromAsset() {
        ArrayList<GlobalClass> loclist = new ArrayList<>();
        ArrayList<String> efuse = new ArrayList<>();
        ArrayList<String> lfuse = new ArrayList<>();
        ArrayList<String> hfuse = new ArrayList<>();
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
            return null;
        }
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray data = obj.getJSONArray("atmega328p");
            for (int i = 0; i<data.length(); i++){
                JSONObject data_inside = data.getJSONObject(i);
                GlobalClass atmega328p = new GlobalClass();
                atmega328p.setFlash((int) data_inside.getDouble("flash"));
                atmega328p.setSram((int) data_inside.getDouble("sram"));
                atmega328p.setEeprom((int) data_inside.getDouble("eeprom"));

                JSONObject fuse_e = data_inside.getJSONObject("efuse");
//                for (int x=0; x<3 ; x++){
//                    efuse.add(fuse_e.getString(""+i+""));
//                } // isoh ra yo :v
                efuse.add(fuse_e.getString("0"));
                efuse.add(fuse_e.getString("1"));
                efuse.add(fuse_e.getString("2"));

                JSONObject fuse_l = data_inside.getJSONObject("lfuse");
//                for (int x=0; x<8 ; x++){
//                    lfuse.add(fuse_l.getString(""+i+""));
//                }

                JSONObject fuse_h = data_inside.getJSONObject("hfuse");
//                for (int x=0; x<8 ; x++){
//                    hfuse.add(fuse_h.getString(""+i+""));
//                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return loclist;
    }


}
