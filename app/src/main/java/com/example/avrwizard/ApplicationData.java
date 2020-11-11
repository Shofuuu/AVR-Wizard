package com.example.avrwizard;

import android.app.Application;

public class ApplicationData extends Application{
    private boolean wizard_info = false;

    public void setWizard_info(boolean wizard_info) {
        this.wizard_info = wizard_info;
    }

    public boolean getWizard_info() { return wizard_info; }
}
