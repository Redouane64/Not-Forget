package com.github.redouane64.views.register

import com.github.redouane64.presenters.register.RegisterPresenter
import com.github.redouane64.views.BaseView

interface RegisterView : BaseView<RegisterPresenter> {
    fun login();
    fun register();
    fun hasValidPassword(): Boolean;
    fun hasValidEmail(): Boolean;
    fun disableRegisterButton();
    fun enableRegisterButton();
    fun gotoTasks();
    fun showRegisteredSuccessfulMessage();
}