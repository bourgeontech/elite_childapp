package com.burgeon.parentapp.Login

/**
 * Created by Ajay K K on 2020-01-08.
 */
class LoginPresenter(var loginview: LoginView?, val loginInteractor: LoginInteractor) :
    LoginInteractor.OnLoginFinishedListener {

    override fun onFailure(msg: String?) {
        loginview?.apply {
            loginError(msg!!)
            hideProgress()
        }
    }

    override fun onUsernameError() {
        loginview?.apply {
            setUsernameError()
            hideProgress()
        }
    }

    override fun onPasswordError() {
        loginview?.apply {
            setPasswordError()
            hideProgress()
        }
    }

    override fun onSuccess(
        id: String,
        name: String,
        mobile: String
    ) {
        loginview?.apply {
            navigateToHome(id,name,mobile)
            hideProgress()
        }
    }

    fun validateCredentials(username: String, password: String) {
        loginview?.showProgress()
        loginInteractor.login(username, password, this)
    }

    fun onDestroy() {
        loginview = null
    }
}