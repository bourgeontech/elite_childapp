package com.burgeon.parentapp.SubjectList

/**
 * Created by Ajay K K on 2020-02-17.
 */
interface uploadDocView {
    fun showProgress()
    fun hideProgress()


    fun fileSubmitSuccess()
    fun fileSubmitError(s: String)

}