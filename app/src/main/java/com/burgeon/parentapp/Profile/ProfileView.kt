package com.burgeon.parentapp.Profile

import com.burgeon.parentapp.Datamodels.childdetailsDatamodel

/**
 * Created by Ajay K K on 2020-02-14.
 */
interface ProfileView {

    fun showProgress()
    fun hideProgress()
    fun setProfileData(child: List<childdetailsDatamodel>?)
    fun serFailure()

}