package com.github.redouane64.infrastructure

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.redouane64.R
import com.github.redouane64.models.Task

class TasksListAdapter(private val list: List<Task>) : RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false);
        return TaskViewHolder(itemView);
    }

    override fun getItemCount(): Int {
        return this.list.size;
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = list[position];
        holder.setTitle(task.title);
        holder.setDescription(task.description);
        holder.setStatus(task.done);
    }
}