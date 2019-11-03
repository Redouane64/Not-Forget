package com.github.redouane64.presenters.tasks

import com.github.redouane64.infrastructure.Constants.API_TOKEN
import com.github.redouane64.models.ApiError
import com.github.redouane64.models.Task
import com.github.redouane64.presenters.BasePresenter
import com.github.redouane64.services.PersistenceService
import com.github.redouane64.services.TasksService
import com.github.redouane64.views.tasks.TasksView

class TasksPresenter(private var view: TasksView?,
                     private val keyValueStore: PersistenceService,
                     private var tasksService: TasksService) : BasePresenter {

    override fun onDestroy() {
        this.view = null;
    }

    public fun fetchTasks() {
        var token = keyValueStore.retrieve<String>(API_TOKEN, String::class.java);
        tasksService.getTasks(token!!, { error -> onTasksFailure(error) }, { tasks -> onTasksFetched(tasks) });

    }

    fun updateTask(task: Task) {
        val token = keyValueStore.retrieve(API_TOKEN, String::class.java);
        tasksService.updateTask(token!!, task,
            { error -> onTaskUpdateFailure(error) },
            { task: Task -> onTaskUpdateSuccess(task) });
    }

    private fun onTaskUpdateFailure(error: ApiError) {
        this.view?.showTaskUpdateFailedMessage();
        this.view?.setTaskUnDone();
    }

    private fun onTaskUpdateSuccess(task: Task) {
        this.view?.showTaskSetToDoneMessage();
    }

    private fun onTasksFetched(tasks: List<Task>) {
        this.view?.displayTasks(tasks);
    }

    private fun onTasksFailure(error: ApiError) {
        this.view?.displayTasks(listOf());
    }

    fun logOut() {
        // remove stored token.
        keyValueStore.remove(API_TOKEN);
    }
}