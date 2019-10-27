package com.github.redouane64.services

import android.content.SharedPreferences

// Key/Value store implementation that uses Android SharedPreferences.
class SharedPreferencesStore(private val store: SharedPreferences) :
    PersistenceService {

    override fun <T: Any> save(key: String, value: T) {
        store.edit().let {
            if (value is String) {
                it.putString(key, value);
            };

            it.commit();
        };
    }

    override fun <T: Any?> retrieve(key: String): T? {
        // create a nullable instance of T, just to check
        // the type of value we retrieve so the right putXXXX
        // method is called.
        val test : T? = null;

        // For now we need only to retrieve String values.
        if(test is String) {
            return store.getString(key, null) as T;
        }

        return null;
    }

}