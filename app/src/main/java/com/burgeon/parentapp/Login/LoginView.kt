package com.burgeon.parentapp.Login

/**
 * Created by Ajay K K on 2020-01-08.
 */
interface LoginView {

    fun showProgress()
    fun hideProgress()
    fun setUsernameError()
    fun setPasswordError()
    fun navigateToHome(
        id: String,
        name: String,
        mobile: String
    )
    fun loginError(msg:String)
}