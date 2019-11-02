package com.github.redouane64

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.github.redouane64.infrastructure.DialogProvider
import com.github.redouane64.models.Task
import com.github.redouane64.presenters.tasks.TaskDetailPresenter
import com.github.redouane64.views.tasks.TaskDetailsView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), TaskDetailsView {

    private lateinit var presenter: TaskDetailPresenter;
    private lateinit var task: Task;

    override fun setPresenter(presenter: TaskDetailPresenter) {
        this.presenter = presenter;
    }

    override fun getDialogProvider(): DialogProvider {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTask(task: Task) {
        this.taskTitle.text = task.title;
        this.taskDescription.text = task.description;
        this.priority.text = task.priority?.name;
        this.category.text = task.category?.name;
        this.status.setText(when(task.done) {
            0 -> R.string.task_undone;
            1 -> R.string.task_done;
            else -> R.string.na;
        });
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        theme.applyStyle(R.style.AppTheme, true);
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getColor(R.color.white));
        toolbar.setNavigationOnClickListener { finish(); }

        this.setTask(Gson().fromJson(intent.getStringExtra("task"), Task::class.java));
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}
