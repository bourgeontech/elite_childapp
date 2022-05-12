package com.app.khmhsschild.SubjectList


/**
 * Created by Ajay K K on 2020-02-17.
 */
class CommunicationPresenter(
    var studentprofileview: CommunicationView,
    var studentprofileInteractor: CommunicationInteractor
) : CommunicationInteractor.onStudentprofileListener {


    override fun submitSuccess() {
        studentprofileview?.apply {
            hideProgress()
            fileSubmitSuccess()
        }
    }

    override fun submitError(s: String) {
        studentprofileview?.apply {
            hideProgress()
            fileSubmitError(s)
        }
    }


    fun submitData(
        teacherId: String,
        studentId: String,
        type: String,
        matter: String
    ) {
        studentprofileview?.showProgress()
        studentprofileInteractor.submitData(  teacherId,studentId, type, matter, this)
    }
}