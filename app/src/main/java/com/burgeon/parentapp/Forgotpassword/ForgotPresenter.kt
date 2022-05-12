package com.burgeon.parentapp.Forgotpassword

/**
 * Created by Ajay K K on 2020-01-08.
 */
class ForgotPresenter(var forgotview: ForgotView?, val forgotInteractor: ForgotInteractor) :
    ForgotInteractor.OnForgotFinishedListener {

    override fun onFailure() {
        forgotview?.apply {
            loginError()
            hideProgress()
        }
    }

    override fun onUsernameError() {
        forgotview?.apply {
            setUsernameError()
            hideProgress()
        }
    }


    override fun onSuccess() {
        forgotview?.apply {
            success()
            hideProgress()
        }
    }

    fun validateCredentials(username: String) {
        forgotview?.showProgress()
        forgotInteractor.forgotpasswd(username, this)
    }

    fun onDestroy() {
        forgotview = null
    }
}