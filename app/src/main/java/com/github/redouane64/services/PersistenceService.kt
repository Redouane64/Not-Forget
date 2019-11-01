package com.github.redouane64.services

// a generic store interface for the purpose of extensibility
// it may be need to provide a different implementation for
// key/value stores, for example use android SharedPrefrences
// or SQLite..etc
interface PersistenceService {
    fun save(key: String, value: Any);
    fun <T> retrieve(key: String, clazz: Class<T>) : T?;
    fun remove(key: String);
}

