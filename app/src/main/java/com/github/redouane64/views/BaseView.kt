package com.github.redouane64.views

interface BaseView<TPresenter> {
    fun setPresenter(presenter: TPresenter);
}