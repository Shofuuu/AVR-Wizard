package com.example.avrwizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GeneratedProgram extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_program);

        Button btnBack = (Button) findViewById(R.id.btnback);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void backtoselectboard(){
        Intent intent = new Intent(this, GenerateCode.class);
        startActivity(intent);
    }
}