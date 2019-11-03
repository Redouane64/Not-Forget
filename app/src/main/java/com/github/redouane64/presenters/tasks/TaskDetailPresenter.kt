package com.github.redouane64.presenters.tasks

import com.github.redouane64.models.Task
import com.github.redouane64.presenters.BasePresenter
import com.github.redouane64.views.tasks.TaskDetailsView

class TaskDetailPresenter(private var view: TaskDetailsView?): BasePresenter {

    private lateinit var task: Task;

    override fun onDestroy() {
        this.view = null;
    }

    fun setTask(task: Task) {
        this.task = task;
    }

    fun displayTask() {
        view?.setTaskTitle(task.title);
        view?.setTaskDescription(task.description);
        view?.setTaskStatus(task.done!!.toInt());
        view?.setCategory(task.category);
        view?.setPriority(task.priority);
        view?.setDate(task.created);
    }
}