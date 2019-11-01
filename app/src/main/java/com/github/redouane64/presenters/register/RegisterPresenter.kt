package com.github.redouane64.presenters.register

import com.github.redouane64.infrastructure.Constants.API_TOKEN
import com.github.redouane64.models.ApiError
import com.github.redouane64.models.RegisterForm
import com.github.redouane64.presenters.BasePresenter
import com.github.redouane64.services.AccountService
import com.github.redouane64.services.SharedPreferencesStore
import com.github.redouane64.views.register.RegisterView

class RegisterPresenter(private var view: RegisterView?,
                        private val store: SharedPreferencesStore,
                        private val accountService: AccountService) : BasePresenter {

    override fun onDestroy() {
        this.view = null;
    }

    fun register(form: RegisterForm) {
        this.view?.disableRegisterButton();
        this.accountService.register(form, { e -> failed(e) }, { r -> success(r) } )
    }

    private fun failed(error: ApiError) {
        this.view?.getDialogProvider()!!.showErrorMessage(error.message);
        this.view?.enableRegisterButton();
    }

    private fun success(result: RegisterForm) {
        this.store.save(API_TOKEN, result.token.toString());
        this.view?.enableRegisterButton();
        this.view?.showRegisteredSuccessfulMessage();
        this.view?.gotoTasks();
    }
}