package com.github.redouane64.views.tasks

import com.github.redouane64.models.Category
import com.github.redouane64.models.Priority
import com.github.redouane64.models.Task
import com.github.redouane64.presenters.tasks.TaskDetailPresenter
import com.github.redouane64.views.BaseView

interface TaskDetailsView : BaseView<TaskDetailPresenter> {
    fun setTaskTitle(title: String?);
    fun setTaskDescription(description: String?);
    fun setPriority(priority: Priority?);
    fun setCategory(category: Category?)
    fun setTaskStatus(status: Int);
}