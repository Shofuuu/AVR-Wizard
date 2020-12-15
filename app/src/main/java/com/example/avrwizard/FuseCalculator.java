package com.example.avrwizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FuseCalculator extends AppCompatActivity {
    Spinner spinnerChip;
    Spinner spinnerFuse;
    String strChip = "atmega328p";
    String strFuse = "lfuse";

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

        final JSONEngine json = (JSONEngine) getApplicationContext();
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

                JSONEngine json = (JSONEngine) getApplicationContext();
                json.setICName(strChip);

                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_IMMERSIVE
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                );

                update_fuse();
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

                update_fuse();
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

        json.setICName("atmega328p");

        final CheckBox cbfuse0 = (CheckBox) findViewById(R.id.cbfuse0);
        final CheckBox cbfuse1 = (CheckBox) findViewById(R.id.cbfuse1);
        final CheckBox cbfuse2 = (CheckBox) findViewById(R.id.cbfuse2);
        final CheckBox cbfuse3 = (CheckBox) findViewById(R.id.cbfuse3);
        final CheckBox cbfuse4 = (CheckBox) findViewById(R.id.cbfuse4);
        final CheckBox cbfuse5 = (CheckBox) findViewById(R.id.cbfuse5);
        final CheckBox cbfuse6 = (CheckBox) findViewById(R.id.cbfuse6);
        final CheckBox cbfuse7 = (CheckBox) findViewById(R.id.cbfuse7);

        cbfuse0.setChecked(!json.getLFuseValue(0));
        cbfuse1.setChecked(!json.getLFuseValue(1));
        cbfuse2.setChecked(!json.getLFuseValue(2));
        cbfuse3.setChecked(!json.getLFuseValue(3));
        cbfuse4.setChecked(!json.getLFuseValue(4));
        cbfuse5.setChecked(!json.getLFuseValue(5));
        cbfuse6.setChecked(!json.getLFuseValue(6));
        cbfuse7.setChecked(!json.getLFuseValue(7));

        final TextView fusename = (TextView) findViewById(R.id.fusename);
        TextView fusedescription = (TextView) findViewById(R.id.fusedescription);

        fusename.setText(json.getLFuseName(0));
        fusedescription.setText(json.getLFuseDesc(0));

        cbfuse0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fusename.setText(cbfuse0.getText());
                update_fusedesc(0);
            }
        });
        cbfuse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fusename.setText(cbfuse1.getText());
                update_fusedesc(1);
            }
        });
        cbfuse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fusename.setText(cbfuse2.getText());
                update_fusedesc(2);
            }
        });
        cbfuse3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fusename.setText(cbfuse3.getText());
                update_fusedesc(3);
            }
        });
        cbfuse4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fusename.setText(cbfuse4.getText());
                update_fusedesc(4);
            }
        });
        cbfuse5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fusename.setText(cbfuse5.getText());
                update_fusedesc(5);
            }
        });
        cbfuse6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fusename.setText(cbfuse6.getText());
                update_fusedesc(6);
            }
        });
        cbfuse7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fusename.setText(cbfuse7.getText());
                update_fusedesc(7);
            }
        });
    }

    private void opengeneratedvalue(){
        Intent intent = new Intent(this, GeneratedValue.class);
        JSONEngine json = (JSONEngine) getApplicationContext();

        json.setIntentSource(json.FROM_FUSE_CALC_INTENT);

        startActivity(intent);
    }

    private void update_fuse(){
        JSONEngine json = (JSONEngine) getApplicationContext();
        String[] fusebit = new String[8];

        CheckBox cbfuse0 = (CheckBox) findViewById(R.id.cbfuse0);
        CheckBox cbfuse1 = (CheckBox) findViewById(R.id.cbfuse1);
        CheckBox cbfuse2 = (CheckBox) findViewById(R.id.cbfuse2);
        CheckBox cbfuse3 = (CheckBox) findViewById(R.id.cbfuse3);
        CheckBox cbfuse4 = (CheckBox) findViewById(R.id.cbfuse4);
        CheckBox cbfuse5 = (CheckBox) findViewById(R.id.cbfuse5);
        CheckBox cbfuse6 = (CheckBox) findViewById(R.id.cbfuse6);
        CheckBox cbfuse7 = (CheckBox) findViewById(R.id.cbfuse7);

        if(strFuse.equals("lfuse")){
            fusebit[0] = json.getLFuseName(0);
            fusebit[1] = json.getLFuseName(1);
            fusebit[2] = json.getLFuseName(2);
            fusebit[3] = json.getLFuseName(3);
            fusebit[4] = json.getLFuseName(4);
            fusebit[5] = json.getLFuseName(5);
            fusebit[6] = json.getLFuseName(6);
            fusebit[7] = json.getLFuseName(7);
        }else if(strFuse.equals("hfuse")){
            fusebit[0] = json.getHFuseName(0);
            fusebit[1] = json.getHFuseName(1);
            fusebit[2] = json.getHFuseName(2);
            fusebit[3] = json.getHFuseName(3);
            fusebit[4] = json.getHFuseName(4);
            fusebit[5] = json.getHFuseName(5);
            fusebit[6] = json.getHFuseName(6);
            fusebit[7] = json.getHFuseName(7);
        }else if(strFuse.equals("efuse")){
            fusebit[0] = json.getEFuseName(0);
            fusebit[1] = json.getEFuseName(1);
            fusebit[2] = json.getEFuseName(2);
            fusebit[3] = json.getEFuseName(3);
            fusebit[4] = json.getEFuseName(4);
            fusebit[5] = json.getEFuseName(5);
            fusebit[6] = json.getEFuseName(6);
            fusebit[7] = json.getEFuseName(7);
        }

        cbfuse0.setText(fusebit[0]);
        cbfuse1.setText(fusebit[1]);
        cbfuse2.setText(fusebit[2]);
        cbfuse3.setText(fusebit[3]);
        cbfuse4.setText(fusebit[4]);
        cbfuse5.setText(fusebit[5]);
        cbfuse6.setText(fusebit[6]);
        cbfuse7.setText(fusebit[7]);
    }

    private void update_fusedesc(int index){
        JSONEngine json = (JSONEngine) getApplicationContext();
        TextView fusedescription = (TextView) findViewById(R.id.fusedescription);

        if(strFuse.equals("efuse")){
            fusedescription.setText(json.getEFuseDesc(index));
        }else if(strFuse.equals("lfuse")){
            fusedescription.setText(json.getLFuseDesc(index));
        }else if(strFuse.equals("hfuse")){
            fusedescription.setText(json.getHFuseDesc(index));
        }
    }
}