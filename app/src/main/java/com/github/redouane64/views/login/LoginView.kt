package com.github.redouane64.views.login

import com.github.redouane64.models.LoginCredentials
import com.github.redouane64.presenters.login.LoginPresenter
import com.github.redouane64.views.BaseView
import com.github.redouane64.infrastructure.DialogProvider

interface LoginView : BaseView<LoginPresenter> {
    fun login(credentials: LoginCredentials);
    fun canLogin() : Boolean;
    fun getDialogProvider(): DialogProvider;
    fun showLoginSucceededMessage();
    fun enableLoginButton();
    fun disableLoginButton();
    fun skipLogin();
    fun register();
}