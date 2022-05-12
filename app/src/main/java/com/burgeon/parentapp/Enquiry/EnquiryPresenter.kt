package com.app.khmhsschild.SubjectList


/**
 * Created by Ajay K K on 2020-02-17.
 */
class EnquiryPresenter(
    var studentprofileview: EnquiryView,
    var studentprofileInteractor: EnquiryInteractor
) : EnquiryInteractor.onStudentprofileListener {


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
        studentId: String,
        type: String,
        matter: String
    ) {
        studentprofileview?.showProgress()
        studentprofileInteractor.submitData(studentId, type, matter, this)
    }
}