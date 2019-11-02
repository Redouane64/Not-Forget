package com.github.redouane64.infrastructure

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.redouane64.R
import com.github.redouane64.models.Task

class TasksListAdapter(
        private val itemClicked: (Task) -> Unit
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val EMPTY_VIEW = 0;
    private val LOADING = 1
    private val LOADED = 2;

    private var tasks: MutableList<Task> = mutableListOf();
    public var isLoading: Boolean = true;

    fun setTasks(tasks: List<Task>) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
        this.notifyDataSetChanged();
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            0 -> NoTasksViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_empty_item, parent, false));
            1 -> LoadingTasksViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_loading_item, parent, false));
            else -> TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false));
        }
    }

    override fun getItemCount(): Int {
        return if(this.tasks.size == 0) 1; else this.tasks.size;
    }

    override fun getItemViewType(position: Int): Int {
        return if(this.tasks.isEmpty() && !isLoading) EMPTY_VIEW else if(isLoading) LOADING else LOADED;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder is TaskViewHolder) {
            val task = tasks[position];
            holder.setTitle(task.title);
            holder.setDescription(task.description);
            holder.setStatus(task.done);
            holder.setClickListener({ this.itemClicked(it) }, task);
        }

    }
}