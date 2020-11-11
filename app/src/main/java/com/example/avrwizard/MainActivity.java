package com.example.avrwizard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApplicationData data = (ApplicationData) getApplicationContext();


        data.setWizard_info(true);
    }

    private void alert_info(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

        alertBuilder.setTitle("AVR Wizard Information");

        alertBuilder.setMessage("Lorem ipsum dolor sit amet, consectetur\n" +
                "adipiscing elit, sed do eiusmod tempor\n" +
                "incididunt ut labore et dolore magna aliqua.\n" +
                "Ut enim ad minim veniam, quis nostrud\n" +
                "exercitation ullamco laboris nisi ut aliquip\n" +
                "ex ea commodo consequat.")
        .setIcon(R.mipmap.ic_launcher)
        .setCancelable(false)
        .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                // jika tombol diklik, maka akan menutup activity ini
                MainActivity.this.finish();
            }
        })
        .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // jika tombol ini diklik, akan menutup dialog
                // dan tidak terjadi apa2
                dialog.cancel();
            }
        });
    }

}