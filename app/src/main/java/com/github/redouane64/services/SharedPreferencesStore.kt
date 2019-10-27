package com.github.redouane64.services

import android.content.SharedPreferences

// Key/Value store implementation that uses Android SharedPreferences.
class SharedPreferencesStore(private val store: SharedPreferences) :
    PersistenceService {

    override fun save(key: String, value: Any) {
        with(store.edit()) {
            if (value is String) {
                putString(key, value);
            };

            apply();
        };
    }

    override fun <T> retrieve(key: String, clazz: Class<T>): T? {

        if(clazz == String::class.java)
            return store.getString(key, null) as T?;

        return null;
    }

}