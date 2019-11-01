package com.github.redouane64.views

import com.github.redouane64.infrastructure.DialogProvider

interface BaseView<TPresenter> {
    fun setPresenter(presenter: TPresenter);
    fun getDialogProvider(): DialogProvider;
}