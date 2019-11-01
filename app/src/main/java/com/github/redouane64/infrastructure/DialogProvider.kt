package com.github.redouane64.infrastructure

import android.content.Context
import android.graphics.Color
import android.widget.Toast

class DialogProvider(private val context: Context) {

    fun showMessage(resId: Int) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
    }

    fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    fun showErrorMessage(message: String) {
        with(Toast.makeText(context, message, Toast.LENGTH_SHORT)) {
            view.setBackgroundColor(Color.RED);
            show();
        }
    }

    fun showErrorMessage(resId: Int) {
        with(Toast.makeText(context, resId, Toast.LENGTH_SHORT)) {
            view.setBackgroundColor(Color.RED);
            show();
        }
    }

}