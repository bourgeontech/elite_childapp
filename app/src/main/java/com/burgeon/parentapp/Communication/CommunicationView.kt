package com.app.khmhsschild.SubjectList


/**
 * Created by Ajay K K on 2020-02-17.
 */
interface CommunicationView {
    fun showProgress()
    fun hideProgress()

    fun fileSubmitSuccess()
    fun fileSubmitError(s: String)

}