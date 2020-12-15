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
    public final int GENERATED_FUSE_OK = 0;
    public final int GENERATED_FUSE_WARNING = 1;
    public final int GENERATED_FUSE_ALERT = 2;

    public final int FROM_NEW_BOARD_INTENT = 0;
    public final int FROM_FUSE_CALC_INTENT = 1;

    public final int IC_LOW_END = 0;
    public final int IC_HIGH_END = 1;

    private int source_intent;

    private String[] user_fuse = new String[3];
    private boolean[] danger_fuse = {true, true, false};
    private boolean clock_source = true;

    private String ic_name;
    private JSONObject IC;
    private JSONObject obj;
    private JSONObject data;
    private JSONArray efuse;
    private JSONArray hfuse;
    private JSONArray lfuse;

    private int delay,freq,pre;

    private String select_freq;

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
//    Get IC
    public String getIC(int index) {
        String list = "";
        try {
            IC = obj.getJSONObject("chip");
            list = IC.getString(String.valueOf(index));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

//    Get Data IC
    public void loadDataIC(){
        try {
            obj = new JSONObject(loadJSONFromAsset());
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

//    FLASH
    public String getFlash() {
        String tmp = null;
        try {
            tmp = data.getString("flash");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tmp;
    }

//    SRAM
    public String getSram(){
        String tmp = "";
        try {
            tmp = data.getString("sram");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tmp;
    }

//    EEPROM
    public String getEeprom(){
        String tmp = "";
        try {
            tmp = data.getString("eeprom");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tmp;
    }

//    Extend Fuse
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
    public String getEFuseDesc(int index){
        String EFuseValue = "";
        try {
            JSONObject fuse_e = data.getJSONObject("efuse");
            efuse = fuse_e.getJSONArray(String.valueOf(index));
            EFuseValue = efuse.getString(1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return EFuseValue;
    }
    public boolean getEFuseValue(int index){
        boolean EFuseValue = false;
        try {
            JSONObject fuse_e = data.getJSONObject("efuse");
            efuse = fuse_e.getJSONArray(String.valueOf(index));
            EFuseValue = efuse.getBoolean(2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return EFuseValue;
    }


//    High Fuse
    public String getHFuseName (int index){
        String HFuseName = "";
        try {
            JSONObject fuse_h = data.getJSONObject("hfuse");
            hfuse = fuse_h.getJSONArray(String.valueOf(index));
            HFuseName = hfuse.getString(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return HFuseName;
    }
    public String getHFuseDesc (int index){
        String HFuseName = "";
        try {
            JSONObject fuse_h = data.getJSONObject("hfuse");
            hfuse = fuse_h.getJSONArray(String.valueOf(index));
            HFuseName = hfuse.getString(1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return HFuseName;
    }
    public boolean getHFuseValue (int index){
        boolean HFuseValue = false;
        try {
            JSONObject fuse_h = data.getJSONObject("hfuse");
            hfuse = fuse_h.getJSONArray(String.valueOf(index));
            HFuseValue = hfuse.getBoolean(2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return HFuseValue;
    }

//    Low Fuse
    public String getLFuseName (int index){
        String LFuseName = "";
        try {
            JSONObject fuse_l = data.getJSONObject("lfuse");
            lfuse = fuse_l.getJSONArray(String.valueOf(index));
            LFuseName = lfuse.getString(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return LFuseName;
    }
    public String getLFuseDesc (int index){
        String LFuseName = "";
        try {
            JSONObject fuse_l = data.getJSONObject("lfuse");
            lfuse = fuse_l.getJSONArray(String.valueOf(index));
            LFuseName = lfuse.getString(1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return LFuseName;
    }
    public boolean getLFuseValue (int index){
        boolean LFuseValue = false;
        try {
            JSONObject fuse_l = data.getJSONObject("lfuse");
            lfuse = fuse_l.getJSONArray(String.valueOf(index));
            LFuseValue = lfuse.getBoolean(2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return LFuseValue;
    }

    public String generatedFuseSummary(int mode){
        final String[] message = {
            "Good choice!, use the calculated fuse value to setting up your AVR IC(s) happy hacking!",
            "We have found enabled fuse that will lock your IC(s) for the entire life cycle! Please confirm it",
            "With that calculated value, are you sure to lock your IC(s) and disable some important feature?"
        };

        return message[mode];
    }

    public String chipInformation(int mode){
        final String[] message = {
            "Low cost 8bit integrated circuit with enough feature to boost your application, help your application to optimize power consumption!",
            "Boost up your application with big memory size for high performance application with full feature. Fast and low power 8bit integrated circuit"
        };

        return message[mode];
    }

    public void setUserHFuse(String fuse){
        user_fuse[0] = fuse;
    }
    public String getUserHFuse(){
        return ("0x" + user_fuse[0]);
    }

    public void setUserLFuse(String fuse){
        user_fuse[1] = fuse;
    }
    public String getUserLFuse(){
        return ("0x" + user_fuse[1]);
    }

    public void setUserEFuse(String fuse){
        user_fuse[2] = fuse;
    }
    public String getUserEFuse(){
        return ("0x" + user_fuse[2]);
    }

    public void setSelectedFrequency(String freq){
        select_freq = freq;
    }

    public String getSelectedFrequency(){
        return select_freq;
    }

    public void setUserEnableSPIEN(boolean fuse){
        danger_fuse[0] = fuse;
    }
    public boolean getUserEnableSPIEN(){
        return danger_fuse[0];
    }

    public void setUserEnableRSTDISBL(boolean fuse){
        danger_fuse[1] = fuse;
    }
    public boolean getUserEnableRSTDISBL(){
        return danger_fuse[1];
    }

    public void setUserEnableBypass(boolean fuse){
        danger_fuse[2] = fuse;
    }
    public boolean getUserEnableBypass(){
        return danger_fuse[2];
    }

    public void setClockSourceExternal(boolean clock){
        clock_source = clock;
    }
    public boolean getClockSourceExternal(){
        return clock_source;
    }

    public String getUARTBaud(){
        return "";
    }

    public void setIntentSource(int source){
        source_intent = source;
    }

    public int getIntentSource(){
        return source_intent;
    }

    private void SetTimePrescaller(int t){ pre = t; }
    private void SetTimeFrequency(int t){freq = t; }
    private void SetTimeDelay(int t){delay = t; }

    private int GetTimePrescaller(){ return pre; }
    private int GetTimeFrequency(){return freq ; }
    private int GetTimeDelay(){return delay; }

    private String GetTimeValue(){
        String value = "";
        value = (Integer.toHexString(65536 - (GetTimeDelay()*(GetTimeFrequency()/GetTimePrescaller()))));
        return value;
    }
}