package com.burgeon.parentapp.Studentprofile

import com.burgeon.parentapp.Datamodels.StudentprofileDetails

/**
 * Created by Ajay K K on 2020-02-17.
 */
class StudentprofilePresenter(
    var studentprofileview: StudentprofileView,
    var studentprofileInteractor: StudentprofileInteractor
) : StudentprofileInteractor.onStudentprofileListener {

    override fun onSuccess(child: StudentprofileDetails?) {
        studentprofileview?.apply {
            hideProgress()
            setProfileData(child)
        }
    }

    override fun onFailure() {
        studentprofileview?.apply {
            hideProgress()
            apiError()
        }
    }

    fun getStudentprofile(studentId: String) {
        studentprofileview?.showProgress()
        studentprofileInteractor.getStudentprofileData(studentId, this)

    }
}