package com.github.redouane64

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.redouane64.infrastructure.ApiClient
import com.github.redouane64.models.LoginCredentials
import com.github.redouane64.presenters.login.LoginPresenter
import com.github.redouane64.services.AccountService
import com.github.redouane64.services.PersistenceService
import com.github.redouane64.services.SharedPreferencesStore
import com.github.redouane64.infrastructure.DialogProvider
import com.github.redouane64.views.login.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var presenter: LoginPresenter;
    private lateinit var dialogProvider: DialogProvider;

    override fun getDialogProvider(): DialogProvider {
        return this.dialogProvider;
    }

    override fun login(credentials: LoginCredentials) {
        this.presenter.login(credentials);
    }

    override fun register() {
        val intent = Intent(this, RegisterActivity::class.java);
        this.startActivity(intent);
    }

    override fun canLogin(): Boolean {
        // For now, we just check if user has entered their credentials.
        return emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty();
    }

    override fun enableLoginButton() {
        this.loginButton.isEnabled = true;
    }

    override fun disableLoginButton() {
        this.loginButton.isEnabled = false;
    }

    // Navigate to main activity.
    override fun skipLogin() {

        val tasksActivityIntent = Intent(this, TasksActivity::class.java);
        startActivity(tasksActivityIntent);
        finish();
    }

    override fun setPresenter(presenter: LoginPresenter) {
        this.presenter = presenter;
    }

    override fun onDestroy() {
        this.presenter.onDestroy();
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCenter.start(getApplication(), "273a29d7-584b-4186-9c52-b6850767b234",
                  Analytics.class, Crashes.class);

        // Apply AppTheme style.
        theme.applyStyle(R.style.AppTheme, true);
        setContentView(R.layout.activity_login)

        this.dialogProvider = DialogProvider(this);
        // initialize locals
        // Create account service that is provides login interaction.
        val client = ApiClient.create();
        val accountService = AccountService(client);
        val keyValueStore : PersistenceService = SharedPreferencesStore(
            this.getSharedPreferences("LOGIN", Context.MODE_PRIVATE));
        this.setPresenter(LoginPresenter(this, accountService, keyValueStore));

        // attach click handlers.
        loginButton.setOnClickListener {

            // validate login.
            if(this.canLogin()) {
                val cred = LoginCredentials(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                );
                this.login(cred);
            } else {
                this.dialogProvider.showMessage(R.string.enter_your_login)
            }

        }

        signUpbutton.setOnClickListener {
            this.register();
        }

        // check if user logged in.
        if(this.presenter.isLoggedIn()) {
            this.skipLogin();
        }

    }

    override fun showLoginSucceededMessage() {
        this.dialogProvider.showMessage(R.string.login_succeeded);
    }
}
