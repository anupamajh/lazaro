package com.carmel.guestjini.Screens.Common.SharedPreference;

import android.content.SharedPreferences;

import java.util.Set;

public class SharedPreferenceHelper {
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    public SharedPreferenceHelper(
            SharedPreferences preferences,
            SharedPreferences.Editor editor
    ) {
        this.preferences = preferences;
        this.editor = editor;
    }

    public void saveStringValue(String key, String value) {
        editor.putString(key, value);
    }

    public void saveLongValue(String key, long value) {
        editor.putLong(key, value);
    }

    public void saveBooleanValue(String key, boolean value) {
        editor.putBoolean(key, value);
    }

    public void saveFloatValue(String key, float value) {
        editor.putFloat(key, value);
    }

    public void saveIntValue(String key, int value) {
        editor.putInt(key, value);
    }

    public void saveStringSetValue(String key, Set<String> value) {
        editor.putStringSet(key, value);
    }

    public void commit() {
        editor.commit();
    }

    public String readStringValue(String key) {
        return preferences.getString(key, "");
    }

    public long readLongValue(String key) {
        return preferences.getLong(key, 0);
    }

    public boolean readBooleanValue(String key) {
        return preferences.getBoolean(key, false);
    }

    public float readFloatValue(String key) {
        return preferences.getFloat(key, 0f);
    }

    public int readIntValue(String key) {
        return preferences.getInt(key, 0);
    }

    public Set<String> readStringSetValue(String key) {
        return preferences.getStringSet(key, null);
    }

}
