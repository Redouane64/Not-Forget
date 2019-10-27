package com.github.redouane64

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.redouane64.infrastructure.ApiClient
import com.github.redouane64.models.LoginCredentials
import com.github.redouane64.presenters.login.LoginPresenter
import com.github.redouane64.services.AccountService
import com.github.redouane64.services.PersistenceService
import com.github.redouane64.services.SharedPreferencesStore
import com.github.redouane64.views.login.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var presenter: LoginPresenter;

    override fun login(credentials: LoginCredentials) {
        this.presenter.login(credentials);
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

    override fun showMessage(resId: Int) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    override fun showErrorMessage(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.view.setBackgroundColor(Color.RED);

        toast.show();
    }

    override fun showLogInSuccessfulMessage() {
        showMessage(R.string.login_succeeded);
    }

    // Navigate to main activity.
    override fun skipLogin() {

        val mainActivityIntent = Intent(this, MainActivity::class.java);
        startActivity(mainActivityIntent);
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

        // Apply AppTheme style.
        theme.applyStyle(R.style.AppTheme, true);
        setContentView(R.layout.activity_login)

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
                this.showMessage(R.string.enter_your_login)
            }

        }

        // check if user logged in.
        if(this.presenter.isLoggedIn()) {
            this.skipLogin();
        }

    }
}
