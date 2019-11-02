package com.github.redouane64.views.tasks

import com.github.redouane64.models.Task
import com.github.redouane64.presenters.tasks.TaskDetailPresenter
import com.github.redouane64.views.BaseView

interface TaskDetailsView : BaseView<TaskDetailPresenter> {
    fun setTask(task: Task);
}