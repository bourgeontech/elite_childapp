package com.burgeon.parentapp.ExamDetails

import com.burgeon.parentapp.Datamodels.ExamDetailsList

class ExamDetailsPresenter(
    val examdetailsview: ExamDetailsView,
    val examDetailsInteractor: ExamDetailsInteractor
) : ExamDetailsInteractor.GetExaminationPresenter {
    override fun onSuccess(id: List<ExamDetailsList>?) {
        examdetailsview?.apply {
            ExamList(id)
            hideProgress()
        }
    }

    override fun onFailure() {
        examdetailsview?.apply {
           ExamListFail()
            hideProgress()
        }
    }

    fun exam(rowid: String, studentId: String) {
        examdetailsview.showProgress()
        examDetailsInteractor.examdetails(rowid, studentId, this)
    }

}