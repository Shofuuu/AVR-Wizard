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
    String spinnerChipText;
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

        List<String> list_freq = new ArrayList<>();
        list_freq.add("1MHz Internal");
        list_freq.add("8MHz Internal");
        list_freq.add("8MHz External");
        list_freq.add("12MHz External");
        list_freq.add("16MHz External");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_freq);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFreq = (Spinner) findViewById(R.id.spinnerfreq);
        spinnerFreq.setAdapter(adapter);
        spinnerFreq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerFreqText = adapterView.getItemAtPosition(i).toString();

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
        TextView txtboarddescription = (TextView) findViewById(R.id.txtboarddescription);
        JSONEngine json = (JSONEngine) getApplicationContext();
        json.setICName("atmega328p");

        String device_info = null;
        if(json.getICName().equals("atmega8") ||
            json.getICName().equals("attiny85")){
            device_info = json.chipInformation(json.IC_LOW_END);
        }else{
            device_info = json.chipInformation(json.IC_HIGH_END);
        }

        txtboarddescription.setText(
            json.getICName() + " AVR devices\n\n" +
            "EEPROM Size : " + json.getEeprom() + "Kb\n" +
            "FLASH Size : " + json.getFlash() + "Kb\n" +
            "SRAM Size : " + json.getSram() + "Kb\n\n" +
            "IC : " + json.getIC(1)
//            device_info
        );
    }

    private void openegeneratedcode(){
        Intent intent = new Intent(this, GeneratedValue.class);
        JSONEngine json = (JSONEngine) getApplicationContext();

        json.setIntentSource(json.FROM_NEW_BOARD_INTENT);

        startActivity(intent);
    }
}