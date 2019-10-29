package com.github.redouane64.infrastructure

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_item.view.*

class TaskViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setTitle(title: String?) { view.taskTitle.text = title ?: "Unnamed"; }
    fun setDescription(description: String?) { view.taskDescription.text = description ?: "No description"; }
    fun setStatus(value: Int?) { view.taskDoneCheckBox.isChecked = value != null && value > 0; }
}