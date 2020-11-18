package com.example.avrwizard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnHelpMe = (Button) findViewById(R.id.btnHelpMe);
        btnHelpMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewbieActivity();
            }
        });

        Button btnAdvanced = (Button) findViewById(R.id.btnAdvanced);
        btnAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdvancedActivity();
            }
        });
    }

    private void openNewbieActivity(){
        Intent intent = new Intent(this, selectedNewbie.class);
        startActivity(intent);
    }

    private void openAdvancedActivity(){
        Intent intent = new Intent(this, selectedAdvanced.class);
        startActivity(intent);
    }
}