package com.github.redouane64

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.redouane64.models.LoginCredentials
import com.github.redouane64.presenters.login.LoginPresenter
import com.github.redouane64.services.AccountService
import com.github.redouane64.views.login.LoginView

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var presenter: LoginPresenter;

    override fun login(credentials: LoginCredentials) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPresenter(presenter: LoginPresenter) {

        // TODO: initialize the service.
        val accountService: AccountService? = null;

        this.presenter = LoginPresenter(this, accountService);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Apply AppTheme style.
        theme.applyStyle(R.style.AppTheme, true);
        setContentView(R.layout.activity_login)
    }
}
