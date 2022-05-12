package com.burgeon.parentapp.Replaylist

import com.burgeon.parentapp.Datamodels.Replaymain

/**
 * Created by Ajay K K on 2020-02-17.
 */
class ReplaylistPresenter(
    var studentprofileview: ReplaylistView,
    var studentprofileInteractor: ReplaylistInteractor
) : ReplaylistInteractor.onStudentprofileListener {

    override fun onSuccess(child: Replaymain?) {
        studentprofileview?.apply {
            hideProgress()
            setProfileData(child)
        }
    }

    override fun onFailure(msg: String?) {
        studentprofileview?.apply {
            hideProgress()
            apiError(msg)
        }
    }

    fun getStudentprofile(studentId: String) {
        studentprofileview?.showProgress()
        studentprofileInteractor.getStudentprofileData(studentId, this)

    }
}