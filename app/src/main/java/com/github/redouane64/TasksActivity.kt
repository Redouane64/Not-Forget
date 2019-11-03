package com.github.redouane64

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.redouane64.infrastructure.ApiClient
import com.github.redouane64.infrastructure.DialogProvider
import com.github.redouane64.infrastructure.TasksListAdapter
import com.github.redouane64.models.Task
import com.github.redouane64.presenters.tasks.TasksPresenter
import com.github.redouane64.services.SharedPreferencesStore
import com.github.redouane64.services.TasksService
import com.github.redouane64.views.tasks.TasksView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_tasks.*
import kotlinx.android.synthetic.main.content_tasks.*
import kotlinx.android.synthetic.main.task_item.*

class TasksActivity : AppCompatActivity(), TasksView {

    private lateinit var presenter: TasksPresenter;
    private lateinit var tasksListAdapter: TasksListAdapter;
    private lateinit var dialogProvider: DialogProvider;

    override fun itemClicked(task: Task) {
        val intent = Intent(this, DetailsActivity::class.java);
        val taskJson = Gson().toJson(task);
        intent.putExtra("task", taskJson);

        startActivity(intent);
    }

    override fun itemCheckToggled(task: Task) {
        task.done = if (this.taskDoneCheckBox.isChecked) 1 else 0;
        this.presenter.updateTask(task);
    }

    override fun getDialogProvider(): DialogProvider {
        return this.dialogProvider;
    }

    override fun logOut() {
        this.presenter.logOut();

        // navigate to login activity
        val intent = Intent(this, LoginActivity::class.java);
        startActivity(intent);
        finish();
    }

    override fun setPresenter(presenter: TasksPresenter) {
        this.presenter = presenter;
    }

    private val func: (Task) -> Unit =  { itemClicked(it) };
    private val itemToggled: (Task) -> Unit = { itemCheckToggled(it) };

    override fun displayTasks(tasks: List<Task>) {
        this.tasksListAdapter.isLoading = false;
        this.tasksListAdapter.setTasks(tasks);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getColor(R.color.white));

        addTaskButton.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        this.dialogProvider = DialogProvider(this);

        val tasksPresenter = TasksPresenter(this,
            SharedPreferencesStore(this.getSharedPreferences("LOGIN", Context.MODE_PRIVATE)),
            TasksService(ApiClient.create())
        );
        this.setPresenter(tasksPresenter);

        this.presenter.fetchTasks();

        this.tasksList.layoutManager = LinearLayoutManager(this);
        this.tasksListAdapter = TasksListAdapter(func, itemToggled);
        this.tasksList.adapter = this.tasksListAdapter;
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.logoutMenuItem) {
            this.logOut();
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        this.presenter.onDestroy();
        super.onDestroy()
    }

    override fun showTaskSetToDoneMessage() {
        this.dialogProvider.showMessage(R.string.task_setto_done);
    }

    override fun showTaskUpdateFailedMessage() {
        this.dialogProvider.showErrorMessage(R.string.update_task_failed);
    }

    override fun setTaskUnDone() {
        this.taskDoneCheckBox.isChecked = false;
    }
}
