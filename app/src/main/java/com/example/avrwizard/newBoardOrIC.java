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

        Button btngeneratecode = (Button) findViewById(R.id.btngeneratecode);
        btngeneratecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openegeneratedcode();
            }
        });
        TextView txtboarddescription = (TextView) findViewById(R.id.txtboarddescription);
        JSONEngine json = (JSONEngine) getApplicationContext();
        json.setIC("atmega328p");

        txtboarddescription.setText("Atmega328P flash size : " + json.getFlash());
    }

    private void openegeneratedcode(){
        Intent intent = new Intent(this, GeneratedProgram.class);
        startActivity(intent);
    }
}