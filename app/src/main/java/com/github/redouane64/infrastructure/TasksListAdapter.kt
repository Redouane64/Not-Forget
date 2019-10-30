package com.github.redouane64.infrastructure

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.redouane64.R
import com.github.redouane64.models.Task

class TasksListAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val EMPTY_VIEW = 0;
    private val LOADING = 1
    private val LOADED = 2;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType) {
            0 -> NoTasksViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_empty_item, parent, false));
            1 -> LoadingTasksViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_loading_item, parent, false));
            else -> TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false));
        }

    }

    override fun getItemCount(): Int {
        return this.tasks.size;
    }

    override fun getItemViewType(position: Int): Int {
        return LOADED;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder is TaskViewHolder) {
            val task = tasks[position];
            holder.setTitle(task.title);
            holder.setDescription(task.description);
            holder.setStatus(task.done);
        }

    }
}