package com.cannonmatthews.gameoflife;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class PreferencesActivity extends PreferenceActivity {

    private static final String OPTION_MINIMUM = "UNDERPOPULATION_VARIABLE";
    private static final String OPTION_MINIMUM_DEFAULT = "2";
    private static final String OPTION_MAXIMUM = "OVERPOPULATION_VARIABLE";
    private static final String OPTION_MAXIMUM_DEFAULT = "3";
    private static final String OPTION_SPAWN = "SPAWN_VARIABLE";
    private static final String OPTION_SPAWN_DEFAULT = "3";
    private static final String OPTION_ANIMATION_SPEED = "ANIMATION_SPEED_VARIABLE";
    private static final String OPTION_ANIMATION_SPEED_DEFAULT = "3";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
    
    public static String getMinimumVariable(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).
            getString(OPTION_MINIMUM, OPTION_MINIMUM_DEFAULT);
    }
    
    public static String getMaximumVariable(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).
            getString(OPTION_MAXIMUM, OPTION_MAXIMUM_DEFAULT);
    }
    
    public static String getSpawnVariable(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).
            getString(OPTION_SPAWN, OPTION_SPAWN_DEFAULT);
    }
    
    public static String getAnimationSpeed(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).
            getString(OPTION_ANIMATION_SPEED, OPTION_ANIMATION_SPEED_DEFAULT);
    }
}
