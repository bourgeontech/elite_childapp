package com.burgeon.parentapp.Dashboard

/**
 * Created by Ajay K K on 2020-02-12.
 */
class DashboardPresenter(
    var dashboardview: DashboardView, var dashboardinteractor: DashboardInteractor
) : DashboardInteractor.onHomeListener {


    override fun onSuccess() {
        dashboardview?.apply {
            setHomeData()
            hideProgress()
        }
    }

    override fun onFailure() {
        dashboardview?.apply {
            apiError()
            hideProgress()
        }
    }

    override fun onSignedSuccess() {
        dashboardview?.apply {
            signedData()
            hideProgress()
        }
    }

    override fun onSignedFailure() {
        dashboardview?.apply {
            signedDataError()
            hideProgress()
        }
    }

    /*fun getHomedata(userId: String?) {
        dashboardview?.showProgress()
        dashboardinteractor.getDashboardData(userId!!, this)
    }*/

    fun getSignindata(userId: String?) {
        dashboardview?.showProgress()
        dashboardinteractor.getSignInData(userId!!, this)
    }

    fun addSignin(userId: String?) {
        dashboardview?.showProgress()
        dashboardinteractor.addSignInData(userId!!, this)
    }
}