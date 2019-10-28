package com.lsandoval.festafimdeano.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {

    private SharedPreferences mSharePreferences;

    public SecurityPreferences(Context mContext) {
        /*
         * Here we get an Instance of the SharedPreferences,
         * based on the Context passed as parameter when
         * creating an instance of this class.
         *
         * Context.MODE_PRIVATE => Means that the SharedPreferences data from this app can ONLY be accessed by itself
         * */
        this.mSharePreferences = mContext.getSharedPreferences("FestaFimAno", Context.MODE_PRIVATE);
    }

    public void storeString(String key, String value) {
        this.mSharePreferences.edit().putString(key, value).apply();
    }

    public String getStoredString(String key) {
        return this.mSharePreferences.getString(key, "");
    }
}
