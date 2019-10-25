package com.github.redouane64.presenters.login

import com.github.redouane64.services.AccountService
import com.github.redouane64.presenters.BasePresenter
import com.github.redouane64.views.login.LoginView

class LoginPresenter(view: LoginView, accountService: AccountService?) : BasePresenter {
    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}