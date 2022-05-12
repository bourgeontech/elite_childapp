package com.burgeon.parentapp.Forgotpassword

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.burgeon.parentapp.Login.LoginActivity
import com.burgeon.parentapp.R

import kotlinx.android.synthetic.main.activity_forgot.*
import java.lang.Exception

class ForgotActivity : AppCompatActivity(), ForgotView {

    private val presenter = ForgotPresenter(this, ForgotInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        btnForgot.setOnClickListener {

            presenter.validateCredentials(etPhone?.text.toString())
        }
    }

    override fun showProgress() {
        pgProgress?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pgProgress?.visibility = View.GONE
    }

    override fun setUsernameError() {
        etPhone?.setError("Enter valid mobile number")
    }

    override fun success() {
        try {
            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
        } catch (e: Exception) {
        }

    }

    override fun loginError() {
        try {
            Toast.makeText(this, "There is some problem", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
        }
    }

}
