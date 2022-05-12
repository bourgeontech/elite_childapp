package com.burgeon.parentapp.Examresult

import com.burgeon.parentapp.Datamodels.ExamresultdetailsDatamodel

/**
 * Created by Ajay K K on 2020-02-13.
 */
class ExamresultPresenter(
    val examresultview: ExamresultView,
    val examresultInteractor: ExamresultInteractor?
) : ExamresultInteractor.onExamresultlistener {

    override fun onSuccess(data: List<ExamresultdetailsDatamodel>?) {
        examresultview?.apply {
            setExamResult(data)
            hideProgress()
        }
    }

    override fun onFailure() {
        examresultview?.apply {
            apiError()
            hideProgress()
        }
    }

    fun getExamData(parentId: String?){
        examresultview.showProgress()
        examresultInteractor?.getExamresult(parentId!!,this)
    }


}