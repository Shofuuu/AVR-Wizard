package com.example.avrwizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class selectMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_menu);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );

        Button fusebtn = (Button) findViewById(R.id.fusebtn);
        fusebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openfusecalc();
            }
        });
        Button btnNewIC = (Button) findViewById(R.id.btnNewIc);
        btnNewIC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opennewic();
            }
        });
        Button btnGenerateCode = (Button) findViewById(R.id.btnGenerateCode);
        btnGenerateCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opengeneratecode();
            }
        });
    }

    private void openfusecalc(){
        Intent intent = new Intent(this, FuseCalculator.class);
        startActivity(intent);
    }

    private void opennewic(){
        Intent intent = new Intent(this,newBoardOrIC.class);
        startActivity(intent);
    }

    private void opengeneratecode(){
        Intent intent = new Intent(this, GenerateCode.class);
        startActivity(intent);
    }
}