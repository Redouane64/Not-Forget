package com.github.redouane64.services

// a generic store interface for the purpose of extensibility
// it may be need to provide a different implementation for
// key/value stores, for example use android SharedPrefrences
// or SQLite..etc
interface PersistenceService {
    fun <T: Any> save(key: String, value: T);
    fun <T: Any?> retrieve(key: String) : T?;
}

