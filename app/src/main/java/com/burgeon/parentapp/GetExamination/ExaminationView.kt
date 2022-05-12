package com.burgeon.parentapp.GetExamination

import com.burgeon.parentapp.Datamodels.GetExaminationList

interface ExaminationView {
    fun showProgress()
    fun hideProgress()
    fun ExamList(id: List<GetExaminationList>?) {}
    fun ExamListFail()
}