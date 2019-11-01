package com.github.redouane64.presenters.register

import android.view.View
import com.github.redouane64.presenters.BasePresenter

class RegisterPresenter(private var view: View?) : BasePresenter {
    override fun onDestroy() {
        this.view = null;
    }
}