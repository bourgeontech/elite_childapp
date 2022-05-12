package com.burgeon.parentapp.Profile

import com.burgeon.parentapp.Datamodels.childdetailsDatamodel

/**
 * Created by Ajay K K on 2020-02-14.
 */
class ProfilePresenter(val profileview: ProfileView, val profileinteractor: ProfileInteractor) :
    ProfileInteractor.onProfileListener {


    override fun onSuccess(child: List<childdetailsDatamodel>?) {
        profileview?.apply {
            setProfileData(child)
            hideProgress()
        }
    }

    override fun onFailure() {
        profileview?.apply {
            onFailure()
            hideProgress()
        }
    }

    fun getProfile(childId: String?) {
        profileview?.showProgress()
        profileinteractor?.getProfileData(childId, this)
    }

}