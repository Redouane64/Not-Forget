package com.github.redouane64.views.tasks

import com.github.redouane64.models.Task
import com.github.redouane64.presenters.tasks.TasksPresenter
import com.github.redouane64.views.BaseView

interface TasksView : BaseView<TasksPresenter> {
    fun displayTasks(tasks: List<Task>);
    fun logOut();
    fun itemClicked(task: Task);
    fun itemCheckToggled(task: Task);
    fun showTaskUpdateFailedMessage();
    fun showTaskSetToDoneMessage();
    fun setTaskUnDone();
}