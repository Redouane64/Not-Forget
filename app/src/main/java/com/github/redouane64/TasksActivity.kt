package com.github.redouane64

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.github.redouane64.presenters.tasks.TasksPresenter
import com.github.redouane64.views.tasks.TasksView

import kotlinx.android.synthetic.main.activity_tasks.*

class TasksActivity : AppCompatActivity(), TasksView {

    private lateinit var presenter: TasksPresenter;

    override fun setPresenter(presenter: TasksPresenter) {
        this.presenter = presenter;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        setActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onDestroy() {
        this.presenter.onDestroy();
        super.onDestroy()
    }

}
