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

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );

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
        Intent intent;
        JSONEngine json = (JSONEngine) getApplicationContext();

        if(json.getIntentSource() == json.FROM_FUSE_CALC_INTENT){
            intent = new Intent(this, FuseCalculator.class);
        }else{
            intent = new Intent(this, newBoardOrIC.class);
        }

        startActivity(intent);
    }
}