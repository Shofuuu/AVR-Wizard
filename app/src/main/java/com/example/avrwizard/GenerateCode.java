package com.example.avrwizard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GenerateCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_code);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );

        Button btnReadADC = (Button) findViewById(R.id.btnCodeADC);
        Button btnReadUART = (Button) findViewById(R.id.btnCodeReadUART);
        Button btnWriteUART = (Button) findViewById(R.id.btnCodeWriteUART);
        Button btnReadButton = (Button) findViewById(R.id.btnCodeReadButton);
        Button btnWriteOutput = (Button) findViewById(R.id.btnCodeOutput);
        Button btnWritePWM = (Button) findViewById(R.id.btnCodePWM);
        Button btnTimer = (Button) findViewById(R.id.btnCodeTimer);

        btnReadADC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codereadadc();
            }
        });
        btnReadUART.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codereaduart();
            }
        });
        btnWriteUART.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codewriteuart();
            }
        });
        btnReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codereadbutton();
            }
        });
        btnWriteOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codewriteoutput();
            }
        });
        btnWritePWM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codewritepwm();
            }
        });
        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codetimer();
            }
        });
    }

    private void codereadadc(){

    }

    private void codereaduart(){

    }

    private void codewriteuart(){

    }

    private void codereadbutton(){

    }

    private void codewriteoutput(){

    }

    private void codewritepwm(){

    }

    private void codetimer(){

    }
}