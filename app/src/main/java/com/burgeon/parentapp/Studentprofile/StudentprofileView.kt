package com.burgeon.parentapp.Studentprofile

import com.burgeon.parentapp.Datamodels.StudentprofileDetails

/**
 * Created by Ajay K K on 2020-02-17.
 */
interface StudentprofileView {
    fun showProgress()
    fun hideProgress()
    fun setProfileData(child: StudentprofileDetails?)
    fun apiError()
}