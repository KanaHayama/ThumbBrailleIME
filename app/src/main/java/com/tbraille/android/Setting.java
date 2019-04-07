package com.tbraille.android;

import android.content.SharedPreferences;

public class Setting {

    private static SharedPreferences preference;

    public static void setPreference(SharedPreferences prefs) {
        preference = prefs;
    }

    public static boolean getIsLeftAssignTrue() {
        return preference.getString("true_side", "left") == "left";
    }

    public static float getSplitterPosition() {
        switch (preference.getString("strong_hand", "right")) {
            case "left":
                return 0.4f;
            case "right":
                return 0.6f;
                default:
                    return 0.5f;
        }
    }

    public static float getSplitterWidth() {
        return 8;
    }

    public static float getPathWidth() {
        return 4;
    }
}
