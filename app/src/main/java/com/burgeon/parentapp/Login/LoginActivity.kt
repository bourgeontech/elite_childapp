package com.burgeon.parentapp.Login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.burgeon.parentapp.Forgotpassword.ForgotActivity
import com.burgeon.parentapp.MainActivity
import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by Ajay K K on 2020-01-08.
 */
class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var preferenceObj: PreferenceRequestHelper
    private val presenter = LoginPresenter(this, LoginInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        preferenceObj = PreferenceRequestHelper(this)

        llSignin.setOnClickListener { validateCredentials() }

        tvForgotpasswd.setOnClickListener {
            startActivity(Intent(this, ForgotActivity::class.java))
        }
    }

    private fun validateCredentials() {
        presenter.validateCredentials(etUsername.text.toString(), etPassword.text.toString())
    }

    override fun showProgress() {
        pgProgress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pgProgress.visibility = View.GONE
    }

    override fun setUsernameError() {
        etUsername.error = getString(R.string.username_error)
    }

    override fun setPasswordError() {
        etPassword.error = getString(R.string.password_error)
    }

    override fun navigateToHome(
        id: String,
        name: String,
        mobile: String
    ) {

        preferenceObj.setValue(Constants.PRES_ID, id)
        preferenceObj.setValue(Constants.PRES_NAME, name)
        preferenceObj.setValue(Constants.PRES_MOBILE, mobile)

        /*preferenceObj.setValue(Constants.PRES_STUDENT_ID, id)
        preferenceObj.setValue(Constants.PRES_STUDENT_NAME, name)
        preferenceObj.setValue(Constants.PRES_MOBILE, "")
        preferenceObj.setValue(Constants.PRES_STUDENT_CLASS, studentClass)
        preferenceObj.setValue(Constants.PRES_STUDENT_CLASS_ID, studentClassid)
        preferenceObj.setValue(Constants.PRES_STUDENT_SECTION, studentSection)
        preferenceObj.setValue(Constants.PRES_STUDENT_SECTION_ID, studentSectionid)
        preferenceObj.setValue(Constants.PRES_STUDENT_SCHOOL, studentSchool)
        preferenceObj.setValue(Constants.PRES_STUDENT_IMAGE, studentImage)*/

        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun loginError(msg: String) {
        Toast.makeText(this@LoginActivity, msg, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}