package com.example.avrwizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GeneratedProgram extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_program);

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

        Button btnBack = (Button) findViewById(R.id.btnback);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backtoselectboard();
            }
        });
        TextView codeFunction = (TextView) findViewById(R.id.codeFunction);
        TextView codeMain = (TextView) findViewById(R.id.codeMain);

        codeFunction.setText(json.getFunctionCode());
        codeMain.setText(json.getMainCode());
    }

    private void backtoselectboard(){
        Intent intent = new Intent(this, selectMenu.class);
        startActivity(intent);
    }
}