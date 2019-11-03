package com.github.redouane64

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.redouane64.infrastructure.DialogProvider
import com.github.redouane64.models.Category
import com.github.redouane64.models.Priority
import com.github.redouane64.models.Task
import com.github.redouane64.presenters.tasks.TaskDetailPresenter
import com.github.redouane64.views.tasks.TaskDetailsView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), TaskDetailsView {

    private lateinit var presenter: TaskDetailPresenter;

    override fun setPresenter(presenter: TaskDetailPresenter) {
        this.presenter = presenter;
    }

    override fun getDialogProvider(): DialogProvider {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTaskTitle(title: String?) {
        this.taskTitle.text = title;
    }

    override fun setTaskDescription(description: String?) {
        this.taskDescription.text = description;
    }

    override fun setPriority(priority: Priority?) {
        with(this.priority) {
            if (priority != null) {
                text = priority.name;
                setBackgroundColor(Color.parseColor(priority.color));
            }
            else {
                setText(R.string.na);
                setBackgroundColor(Color.parseColor("#eeeeee"));
            }
        }
    }

    override fun setCategory(category: Category?) {
        if(category != null)
            this.category.text = category.name;
        else
            this.category.setText(R.string.na);
    }

    override fun setTaskStatus(status: Int) {

        with(this.status) {
            val (text, color) = when (status) {
                0 -> Pair(R.string.task_undone, R.color.red);
                1 -> Pair(R.string.task_done, R.color.green);
                else -> Pair(R.string.na, R.color.grey);
            };
            setText(text);
            setTextColor(resources.getColor(color, null));
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        theme.applyStyle(R.style.AppTheme, true);
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getColor(R.color.white));
        toolbar.setNavigationOnClickListener { finish(); }

        this.setPresenter(TaskDetailPresenter(this));
        this.presenter.setTask(Gson().fromJson(intent.getStringExtra("task"), Task::class.java));
        this.presenter.displayTask();
    }

}
