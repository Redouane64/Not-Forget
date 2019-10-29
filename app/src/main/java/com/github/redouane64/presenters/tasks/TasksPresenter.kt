package com.github.redouane64.presenters.tasks

import android.view.View
import com.github.redouane64.presenters.BasePresenter
import com.github.redouane64.services.TasksService

class TasksPresenter(private var view: View?,
                     private var tasksService: TasksService) : BasePresenter {

    override fun onDestroy() {
        this.view = null;
    }

    
}