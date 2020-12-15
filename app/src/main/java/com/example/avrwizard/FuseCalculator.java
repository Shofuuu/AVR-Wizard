package com.example.avrwizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class FuseCalculator extends AppCompatActivity {
    Spinner spinnerChip;
    Spinner spinnerFuse;
    String strChip;
    String strFuse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuse_calculator);

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
        json.setUserEnableBypass(false);

        spinnerChip = (Spinner) findViewById(R.id.spinnericname);
        List<String> list_chip = new ArrayList<>();
        for(int x=1;x<=5;x++){
            list_chip.add(json.getIC(x));
        }

        final ArrayAdapter<String> adapter_chip = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_chip);
        adapter_chip.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerChip.setAdapter(adapter_chip);
        spinnerChip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                strChip = adapterView.getItemAtPosition(i).toString();

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

        spinnerFuse = (Spinner) findViewById(R.id.spinnerfuse);
        List<String> list_fuse = new ArrayList<>();
        list_fuse.add("lfuse");
        list_fuse.add("hfuse");
        list_fuse.add("efuse");

        final ArrayAdapter<String> adapter_fuse = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_fuse);
        adapter_fuse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFuse.setAdapter(adapter_fuse);
        spinnerFuse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                strFuse = adapterView.getItemAtPosition(i).toString();

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

        Button btngeneratefuse = (Button) findViewById(R.id.btngeneratefuse);
        btngeneratefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opengeneratedvalue();
            }
        });
    }

    private void opengeneratedvalue(){
        Intent intent = new Intent(this, GeneratedValue.class);
        JSONEngine json = (JSONEngine) getApplicationContext();

        json.setIntentSource(json.FROM_FUSE_CALC_INTENT);

        startActivity(intent);
    }
}