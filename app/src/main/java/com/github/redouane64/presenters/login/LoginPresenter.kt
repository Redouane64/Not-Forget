package com.github.redouane64.presenters.login

import com.github.redouane64.R
import com.github.redouane64.models.ApiError
import com.github.redouane64.models.ApiToken
import com.github.redouane64.models.LoginCredentials
import com.github.redouane64.presenters.BasePresenter
import com.github.redouane64.services.AccountService
import com.github.redouane64.services.PersistenceService
import com.github.redouane64.views.login.LoginView

class LoginPresenter(var view: LoginView?,
                     var accountService: AccountService,
                     var keyValueStore: PersistenceService) : BasePresenter {

    companion object {
        const val API_TOKEN = "API_TOKEN";
    }

    override fun onDestroy() {
        view = null;
    }

    fun login(credentials: LoginCredentials) {
        view?.disableLoginButton();
        accountService.login(credentials, { apiToken -> onSuccessLogin(apiToken) }, { apiError -> onFailedLogin(apiError) });
    }

    // Check of user already has a token and return it.
    fun isLoggedIn() : Boolean {
        val token = this.keyValueStore.retrieve(API_TOKEN, String::class.java);

        if(token != null) {
            view?.showMessage(R.string.logged_in)
            return true;
        }

        return false;
    }

    private fun onFailedLogin(apiError: ApiError) {
        view?.showErrorMessage(apiError.message);
        view?.enableLoginButton();
    }

    private fun onSuccessLogin(apiToken: ApiToken) {
        view?.showLogInSuccessfulMessage();
        view?.enableLoginButton();

        this.keyValueStore.save(API_TOKEN, apiToken.apiToken);

        view?.skipLogin();
    }
}