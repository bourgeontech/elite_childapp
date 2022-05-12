package com.burgeon.parentapp.ExamDetails

import com.burgeon.parentapp.Datamodels.ExamDetailsList

interface ExamDetailsView {
    fun showProgress()
    fun hideProgress()
    fun ExamList(id: List<ExamDetailsList>?) {

    }
    fun ExamListFail()
}