package com.burgeon.parentapp.OnlineClass

import com.burgeon.parentapp.Datamodels.MyConference
import com.burgeon.parentapp.Datamodels.OnlineClassPojo
import com.burgeon.parentapp.Datamodels.childdetailsDatamodel

/**
 * Created by Ajay K K on 2020-02-14.
 */
interface OnlineView {

    fun showProgress()
    fun hideProgress()
    fun setClassData(conferences: List<MyConference>)
    fun serFailure()

}