package org.openintents.flashlight;


import android.app.Activity;
import android.util.Log;
import android.view.WindowManager;

class BrightnessNew extends Brightness {

    public static final String TAG = "BrightnessNew1_5";
    static private java.lang.reflect.Field fieldScreenBrightness;

    /* class initialization fails when this throws an exception */
    static {
        try {

            fieldScreenBrightness = WindowManager.LayoutParams.class.getField("screenBrightness");

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private Activity activity;

    public BrightnessNew(Activity activity) {

        this.activity = activity;
    }

    /* calling here forces class initialization */
    public static void checkAvailable() {
    }

    public void setBrightness(float val) {
        Log.d(TAG, "brightness set to >" + val);
        WindowManager.LayoutParams lp = this.activity.getWindow().getAttributes();

        // SDK 1.5 call:
        //lp.screenBrightness=val;

        try {
            fieldScreenBrightness.setFloat(lp, val);
        } catch (IllegalAccessException e) {
            Log.d(TAG, "Setting brightness failed");
        }

        this.activity.getWindow().setAttributes(lp);
    }
}