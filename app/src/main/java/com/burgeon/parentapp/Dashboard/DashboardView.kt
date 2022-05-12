package com.burgeon.parentapp.Dashboard

/**
 * Created by Ajay K K on 2020-02-12.
 */
interface DashboardView {

    fun showProgress()
    fun hideProgress()
    fun setHomeData()
    fun signedData()
    fun signedDataError()
    fun apiError()
}