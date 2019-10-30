package com.github.redouane64

import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.redouane64.infrastructure.ApiClient
import com.github.redouane64.infrastructure.TasksListAdapter
import com.github.redouane64.models.Task
import com.github.redouane64.presenters.tasks.TasksPresenter
import com.github.redouane64.services.SharedPreferencesStore
import com.github.redouane64.services.TasksService
import com.github.redouane64.views.tasks.TasksView

import kotlinx.android.synthetic.main.activity_tasks.*
import kotlinx.android.synthetic.main.content_tasks.*

class TasksActivity : AppCompatActivity(), TasksView {

    private lateinit var presenter: TasksPresenter;
    private lateinit var taskListAdapter: TasksListAdapter;

    override fun setPresenter(presenter: TasksPresenter) {
        this.presenter = presenter;
    }

    override fun createTasksList(list: List<Task>) {

    }

    override fun getListAdapter() : TasksListAdapter {
        return this.taskListAdapter;
    }

    override fun setTasks(tasks: List<Task>) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        setActionBar(toolbar)

        addTaskButton.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val tasksPresenter = TasksPresenter(this,
            SharedPreferencesStore(this.getSharedPreferences("LOGIN", Context.MODE_PRIVATE)),
            TasksService(ApiClient.create())
        );
        this.setPresenter(tasksPresenter);

    }

    override fun onDestroy() {
        this.presenter.onDestroy();
        super.onDestroy()
    }

}
