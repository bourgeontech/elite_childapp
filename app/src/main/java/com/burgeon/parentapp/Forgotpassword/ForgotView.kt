package com.burgeon.parentapp.Forgotpassword

/**
 * Created by Ajay K K on 2020-01-08.
 */
interface ForgotView {

    fun showProgress()
    fun hideProgress()
    fun setUsernameError()
    fun success()
    fun loginError()
}