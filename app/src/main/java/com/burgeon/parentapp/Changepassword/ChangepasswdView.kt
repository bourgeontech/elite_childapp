package com.burgeon.parentapp.Changepassword

/**
 * Created by Ajay K K on 2020-02-24.
 */
interface ChangepasswdView {
    fun showProgress()
    fun hideProgress()
    fun setPasswordError()
    fun passwdChangeerror(msg: String?)
    fun passwdChangesuccess()
    fun passwdMismatcherror()
    fun passwdNewerror()
}