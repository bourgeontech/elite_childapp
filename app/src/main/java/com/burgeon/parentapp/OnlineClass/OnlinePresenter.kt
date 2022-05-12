package com.burgeon.parentapp.OnlineClass

import com.burgeon.parentapp.Datamodels.MyConference

/**
 * Created by Ajay K K on 2020-02-14.
 */
class OnlinePresenter(val profileview: OnlineView, val profileinteractor: OnlineInteractor) :
    OnlineInteractor.onProfileListener {




    override fun onFailure() {
        profileview?.apply {
            onFailure()
            hideProgress()
        }
    }

    override fun onSuccess(conferences: List<MyConference>) {
        profileview?.apply {
            setClassData(conferences)
            hideProgress()
        }
    }

    fun getClass(childId: String?) {
        profileview?.showProgress()
        profileinteractor?.getData(childId, this)
    }

}