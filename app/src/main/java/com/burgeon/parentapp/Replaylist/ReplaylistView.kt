package com.burgeon.parentapp.Replaylist

import com.burgeon.parentapp.Datamodels.Replaymain

/**
 * Created by Ajay K K on 2020-02-17.
 */
interface ReplaylistView {
    fun showProgress()
    fun hideProgress()
    fun setProfileData(child: Replaymain?)
    fun apiError(msg: String?)
}