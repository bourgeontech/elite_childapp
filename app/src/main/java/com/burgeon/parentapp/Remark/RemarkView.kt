package com.burgeon.parentapp.Remark

import com.burgeon.parentapp.Datamodels.RemarkDetails

interface RemarkView {
    fun showProgress()
    fun hideProgress()
    fun RemarkListFail()
    fun RemarkList(id: List<RemarkDetails>?) {}
   fun  RemarkDelete(message:String)
   fun  RemarkDeleteFail(message:String)
}