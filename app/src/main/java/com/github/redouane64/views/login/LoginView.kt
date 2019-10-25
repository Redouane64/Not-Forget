package com.github.redouane64.views.login

import com.github.redouane64.models.LoginCredentials
import com.github.redouane64.presenters.login.LoginPresenter
import com.github.redouane64.views.BaseView

interface LoginView : BaseView<LoginPresenter> {
    fun login(credentials: LoginCredentials);
}