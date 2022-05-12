package com.burgeon.parentapp.Remark

import com.burgeon.parentapp.Datamodels.RemarkDetails

class RemarkPresenter(var remarkView: RemarkView?, val remarkInteractor: RemarkInteractor) :
    RemarkInteractor.GetExaminationPresenter {
    override fun onSuccess(id: List<RemarkDetails>?) {
     remarkView?.apply {
         hideProgress()
         RemarkList(id)
     }
    }

    override fun onFailure() {
        remarkView?.apply {
            hideProgress()
            RemarkListFail()
        }
    }

    override fun onDeleteSuccess(message: String) {
        remarkView?.apply {
            hideProgress()
            RemarkDelete(message)
        }
    }

    override fun onDeleteFail(message: String) {
        remarkView?.apply {
            hideProgress()
            RemarkDeleteFail(message)
        }
    }

    fun remark(studentId: String?) {
        remarkView?.showProgress()
        remarkInteractor.exam(studentId, this)
    }
    fun onDestroy() {
       remarkView=null
    }

    fun delete(id: String) {
        remarkView?.showProgress()
        remarkInteractor.delete(id, this)
    }
}