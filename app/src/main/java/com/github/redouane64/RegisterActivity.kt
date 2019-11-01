package com.github.redouane64

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.redouane64.views.register.RegisterView
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterView {

    override fun login() {
        // go back to log in activity.
        finish();
    }

    override fun register() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        this.loginButton.setOnClickListener { login(); }
    }
}
