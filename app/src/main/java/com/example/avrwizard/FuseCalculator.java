package com.example.avrwizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FuseCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuse_calculator);

        Button btngeneratefuse = (Button) findViewById(R.id.btngeneratecode);
        btngeneratefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opengeneratedvalue();
            }
        });
    }

    private void opengeneratedvalue(){
        Intent intent = new Intent(this, GeneratedValue.class);
        startActivity(intent);
    }
}