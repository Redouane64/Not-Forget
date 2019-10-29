package com.github.redouane64.presenters.tasks

import com.github.redouane64.models.ApiError
import com.github.redouane64.models.Task
import com.github.redouane64.presenters.BasePresenter
import com.github.redouane64.presenters.login.LoginPresenter
import com.github.redouane64.services.PersistenceService
import com.github.redouane64.services.TasksService
import com.github.redouane64.views.tasks.TasksView

class TasksPresenter(private var view: TasksView?,
                     private val keyValueStore: PersistenceService,
                     private var tasksService: TasksService) : BasePresenter {

    override fun onDestroy() {
        this.view = null;
    }

    fun fetchTasks() {

        var token = keyValueStore.retrieve<String>(LoginPresenter.API_TOKEN, String::class.java);
        tasksService.getTasks(token!!, { error -> onTasksFailure(error) }, { tasks -> onTasksFetched(tasks) });

    }

    private fun onTasksFetched(tasks: List<Task>) {
        view?.createList(tasks);
    }

    private fun onTasksFailure(error: ApiError) {

    }
}