package com.github.redouane64.models

data class Task(
    var id: Int,
    var title: String,
    var description: String,
    var done: Boolean,
    var deadline: Long,
    var created: Long
);
