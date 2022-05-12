package com.burgeon.parentapp.SubjectDetails

import okhttp3.ResponseBody

/**
 * Created by Ajay K K on 2020-02-17.
 */
class SubjectDetailsPresenter(
    var studentprofileview: SubjectDetailsView,
    var studentprofileInteractor: SubjectDetailsInteractor
) : SubjectDetailsInteractor.onStudentprofileListener {

    override fun onSuccess(child: ResponseBody?) {
        studentprofileview?.apply {
            hideProgress()
            setSubjectData(child)
        }
    }

    override fun onFailure() {
        studentprofileview?.apply {
            hideProgress()
            error()
        }
    }

    //Abp return

    override fun onQuestionSuccess(msg: Any?) {
        studentprofileview?.apply {
            hideProgress()
            questionSuccess(msg)
        }
    }

    override fun onQuestionFailure(msg: Any?) {
        studentprofileview?.apply {
            hideProgress()
            questioError(msg)
        }
    }

    fun getSubject(fileurl: String) {
        studentprofileview?.showProgress()
        studentprofileInteractor.getSubjectData(fileurl, this)
    }

    fun sendQuestion(
        userId: String, contentId: String, comment: String
    ) {
        studentprofileview?.showProgress()
        studentprofileInteractor.setQuestion(
            userId, contentId,
            comment, this
        )
    }

    fun sendAudio(
        userId: String, contentId: String, filepath: String
    ) {
        studentprofileview?.showProgress()
        studentprofileInteractor.sendAudio(
            userId, contentId,
            filepath, this
        )
    }
}