package com.example.avrwizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class newBoardOrIC extends AppCompatActivity {
    String spinnerFreqText;
    String spinnerChipText = "atmega328p";
    Spinner spinnerFreq;
    Spinner spinnerChip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_board_or_i_c);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_IMMERSIVE
            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
        );

        JSONEngine json = (JSONEngine) getApplicationContext();
        json.loadDataIC();

        json.setIntentSource(json.FROM_NEW_BOARD_INTENT);
        json.setUserEnableBypass(true);

        List<String> list_freq = new ArrayList<>();
        list_freq.add("8MHz Internal");
        list_freq.add("12MHz External");
        list_freq.add("16MHz External");

        List<String> list_chip = new ArrayList<>();
        for(int x=1;x<=5;x++){
            list_chip.add(json.getIC(x));
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_freq);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> adapter_chip = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_chip);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerChip = (Spinner) findViewById(R.id.spinnerboardname);
        spinnerChip.setAdapter(adapter_chip);
        spinnerChip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerChipText = adapterView.getItemAtPosition(i).toString();
                JSONEngine json = (JSONEngine) getApplicationContext();

                TextView txtboarddescription = (TextView) findViewById(R.id.txtboarddescription);
                json.setICName(spinnerChipText);

                String device_info = null;
                if(json.getICName().equals("atmega8") ||
                        json.getICName().equals("attiny85")){
                    device_info = json.chipInformation(json.IC_LOW_END);
                }else{
                    device_info = json.chipInformation(json.IC_HIGH_END);
                }

                txtboarddescription.setText(
                    json.getICName() + " AVR devices\n\n" +
                    "EEPROM Size : " + json.getEeprom() + "\n" +
                    "FLASH Size : " + json.getFlash() + "\n" +
                    "SRAM Size : " + json.getSram() + "\n\n" +
                    device_info
                );

                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                );
            }
        });

        spinnerFreq = (Spinner) findViewById(R.id.spinnerfreq);
        spinnerFreq.setAdapter(adapter);
        spinnerFreq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String spFreqTxt = adapterView.getItemAtPosition(i).toString();
                JSONEngine json = (JSONEngine) getApplicationContext();

                if(spFreqTxt.equals("8MHz Internal")) {
                    json.setSelectedFrequency("8000000");
                    json.setClockSourceExternal(false);

                    if(spinnerChipText.equals("atmega328p")){
                        json.setUserHFuse("D9");
                        json.setUserLFuse("E2");
                        json.setUserEFuse("FF");
                    }else if(spinnerChipText.equals("atmega8")){
                        json.setUserLFuse("E4");
                        json.setUserEFuse("FF");
                        json.setUserHFuse("CA");
                    }else if(spinnerChipText.equals("atmega8")){
                        json.setUserLFuse("E4");
                        json.setUserEFuse("FF");
                        json.setUserHFuse("CA");
                    }

                }else if(spFreqTxt.equals("12MHz External")) {
                    json.setSelectedFrequency("12000000");
                    json.setClockSourceExternal(true);
                }else if(spFreqTxt.equals("16MHz External")) {
                    json.setSelectedFrequency("16000000");
                    json.setClockSourceExternal(true);
                }

                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                );
            }
        });

        Button btngeneratecode = (Button) findViewById(R.id.btngeneratecode);
        btngeneratecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openegeneratedcode();
            }
        });
    }

    private void openegeneratedcode(){
        Intent intent = new Intent(this, GeneratedValue.class);
        JSONEngine json = (JSONEngine) getApplicationContext();

        startActivity(intent);
    }
}