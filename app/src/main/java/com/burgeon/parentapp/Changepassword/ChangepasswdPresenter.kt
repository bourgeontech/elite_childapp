package com.burgeon.parentapp.Changepassword

/**
 * Created by Ajay K K on 2020-02-24.
 */
class ChangepasswdPresenter(
    val changepasswdView: ChangepasswdView,
    val changepasswdInteractor: ChangepasswdInteractor
) :
    ChangepasswdInteractor.onPasswdchangedListere {

    override fun onOldPasswderror() {
        changepasswdView?.apply {
            hideProgress()
            setPasswordError()
        }
    }

    override fun onNewPasswderror() {
        changepasswdView?.apply {
            hideProgress()
            passwdNewerror()
        }
    }

    override fun onPasswdmismatcherror() {
        changepasswdView?.apply {
            hideProgress()
            passwdMismatcherror()
        }
    }

    override fun onSuccess() {
        changepasswdView?.apply {
            hideProgress()
            passwdChangesuccess()
        }
    }

    override fun onFailure(msg: String?) {
        changepasswdView?.apply {
            hideProgress()
            passwdChangeerror(msg)
        }
    }

    fun onChangepasswd(
        id: String, oldpasswd: String, newpasswd: String,
        repasswd: String
    ) {
        changepasswdView?.showProgress()
        changepasswdInteractor?.changePasswd(id, oldpasswd, newpasswd, repasswd, this)
    }

}