package com.burgeon.parentapp.Examresult

import com.burgeon.parentapp.Datamodels.ExamresultdetailsDatamodel

/**
 * Created by Ajay K K on 2020-02-13.
 */
interface ExamresultView {

    fun showProgress()
    fun hideProgress()
    fun setExamResult(data: List<ExamresultdetailsDatamodel>?)
    fun apiError()

}