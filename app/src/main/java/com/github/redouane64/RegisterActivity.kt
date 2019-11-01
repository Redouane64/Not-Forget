package com.github.redouane64

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.github.redouane64.infrastructure.ApiClient
import com.github.redouane64.infrastructure.DialogProvider
import com.github.redouane64.models.RegisterForm
import com.github.redouane64.presenters.register.RegisterPresenter
import com.github.redouane64.services.AccountService
import com.github.redouane64.services.SharedPreferencesStore
import com.github.redouane64.views.register.RegisterView
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterView {

    private lateinit var presenter: RegisterPresenter;
    private lateinit var dialogProvider: DialogProvider;

    override fun getDialogProvider(): DialogProvider {
        return this.dialogProvider;
    }

    override fun login() {
        // go back to log in activity.
        finish();
    }

    override fun register() {

        if(emailEditText.text.isEmpty() || passwordEditText.text.isEmpty() || usernameEditText.text.isEmpty()) {
            this.dialogProvider.showErrorMessage(R.string.empty_fields_error)
            return;
        }

        if(!this.hasValidEmail()) {
            this.dialogProvider.showErrorMessage(R.string.invalid_email)
            return;
        }

        if(!this.hasValidPassword()) {
            this.dialogProvider.showErrorMessage(R.string.passwords_not_match)
            return;
        }

        this.presenter.register(
            RegisterForm(this.usernameEditText.text.toString(),
                         this.emailEditText.text.toString(),
                         this.passwordEditText.text.toString(),
                         null));
    }

    override fun hasValidEmail(): Boolean = emailEditText.validEmail();

    private fun EditText.validEmail(): Boolean = EMAIL_ADDRESS.matcher(text.toString()).matches();

    override fun hasValidPassword(): Boolean = repeatPasswordEditText.text.toString() == passwordEditText.text.toString();

    override fun setPresenter(presenter: RegisterPresenter) {
        this.presenter = presenter;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        this.dialogProvider = DialogProvider(this);

        this.loginButton.setOnClickListener { login(); }
        this.registerButton.setOnClickListener { register(); }

        this.setPresenter(RegisterPresenter(
            this,
            SharedPreferencesStore(this.getSharedPreferences("LOGIN", Context.MODE_PRIVATE)),
            AccountService(ApiClient.create())));
    }

    override fun disableRegisterButton() { this.registerButton.isEnabled = false; };

    override fun enableRegisterButton() { this.registerButton.isEnabled = true; };

    override fun gotoTasks() {
        val intent = Intent(this, TasksActivity::class.java);
        this.startActivity(intent);
        this.finishAndRemoveTask();
    }

    override fun showRegisteredSuccessfulMessage() {
        this.dialogProvider.showMessage(R.string.register_success);
    }
}
