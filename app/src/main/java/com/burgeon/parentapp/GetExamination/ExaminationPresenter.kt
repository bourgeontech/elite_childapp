package com.burgeon.parentapp.GetExamination

import com.burgeon.parentapp.Datamodels.GetExaminationList

class ExaminationPresenter(
    var examinationView: ExaminationView?, val examinationInteractor: ExaminationInteractor

) : ExaminationInteractor.GetExaminationPresenter {


    override fun onSuccess(id: List<GetExaminationList>?) {
        examinationView?.apply {
            ExamList(id)
            hideProgress()
        }
    }

    override fun onFailure() {
        examinationView?.apply {
            ExamListFail()
            hideProgress()
        }
    }

    fun exam(userId: String?) {
        examinationView?.showProgress()
        examinationInteractor.exam(userId, this)
    }

    fun onDestroy() {
        examinationView = null
    }

}