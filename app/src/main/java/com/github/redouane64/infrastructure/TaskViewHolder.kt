package com.github.redouane64.infrastructure

import android.graphics.Color
import android.view.View
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.github.redouane64.models.Category
import com.github.redouane64.models.Priority
import com.github.redouane64.models.Task
import kotlinx.android.synthetic.main.task_item.view.*

class TaskViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setTitle(title: String?) { view.taskTitle.text = title ?: "Unnamed"; }
    fun setDescription(description: String?) { view.taskDescription.text = description ?: "No description"; }
    fun setStatus(value: Int?) { view.taskDoneCheckBox.isChecked = value != null && value > 0; }
    fun setClickListener(clickListener: (Task) -> Unit, task: Task) {
        view.shell.setOnClickListener { clickListener(task); }
    }
    fun setCheckedListener(checkListener: (Task, Int, CheckBox) -> Unit, task: Task, position: Int) {
        view.taskDoneCheckBox.setOnClickListener {
            checkListener(task, position,(it as CheckBox));
        }
    }
    fun setTaskPriority(priority: Priority?) {
        if (priority != null) {
            view.taskItem.setBackgroundColor(Color.parseColor(priority.color));
        } else
        {
            view.taskItem.setBackgroundColor(Color.WHITE);
        }
    }
}