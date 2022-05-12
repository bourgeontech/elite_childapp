package com.burgeon.parentapp.SubjectList

/**
 * Created by Ajay K K on 2020-02-17.
 */
class uploadDocPresenter(
    var studentprofileview: uploadDocView,
    var studentprofileInteractor: uploadDocInteractor
) : uploadDocInteractor.onStudentprofileListener {

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
        homework_id: String,
        studentId: String,
        title: String,
        remark: String,
        docPaths: ArrayList<String>,
        clicked: Boolean,
        type: String?
    ) {
        studentprofileview?.showProgress()
        studentprofileInteractor.submitData(homework_id, studentId, title, remark, docPaths,clicked,type, this)
    }


}