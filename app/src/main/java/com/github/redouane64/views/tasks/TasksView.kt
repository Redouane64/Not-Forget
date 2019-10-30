package com.github.redouane64.views.tasks

import com.github.redouane64.infrastructure.TasksListAdapter
import com.github.redouane64.models.Task
import com.github.redouane64.presenters.tasks.TasksPresenter
import com.github.redouane64.views.BaseView

interface TasksView : BaseView<TasksPresenter> {
    fun createTasksList(list: List<Task>);
    fun getListAdapter(): TasksListAdapter;
    fun setTasks(tasks: List<Task>);
}