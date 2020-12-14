package com.example.avrwizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class newBoardOrIC extends AppCompatActivity {

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

        txtboarddescription.setText(
                json.getICName() + " flash size : " + json.getFlash() + "\n" +
                json.getICName() + " eeprom size : " + json.getEeprom() + "\n" +
                json.getICName() + " sram size : " + json.getSram() + "\n" +
                json.getICName() + " efuse 1 : " + json.getEFuseName(0) + "\n" +
                json.getICName() + " EFuse Value : " + json.getEFuseValue(0)

        );
    }

    private void openegeneratedcode(){
        Intent intent = new Intent(this, GeneratedProgram.class);
        startActivity(intent);
    }
}