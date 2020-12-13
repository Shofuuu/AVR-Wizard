package com.example.avrwizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GeneratedValue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_value);

        Button btnDone = (Button) findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backtoselectmenu();
            }
        });
        Button btntakemeback = (Button) findViewById(R.id.btnTakeMeBack);
        btntakemeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backtofusecalc();
            }
        });
    }

    private void backtoselectmenu(){
        Intent intent = new Intent(this, selectMenu.class);
        startActivity(intent);
    }

    private void backtofusecalc(){
        Intent intent = new Intent(this, FuseCalculator.class);
        startActivity(intent);
    }
}