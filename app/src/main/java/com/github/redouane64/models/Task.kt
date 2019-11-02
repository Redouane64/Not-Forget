package com.github.redouane64.models

import com.google.gson.annotations.Expose

data class Task(
    var id: Int,
    var title: String?,
    var description: String?,
    var done: Int?,
    var deadline: Long?,
    var created: Long?,

    var category: Category?,
    var priority: Priority?
);