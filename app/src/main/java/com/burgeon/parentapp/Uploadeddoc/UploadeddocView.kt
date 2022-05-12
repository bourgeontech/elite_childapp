package com.burgeon.parentapp.Uploadeddoc

import android.content.Intent
import com.burgeon.parentapp.Datamodels.Uploadeddetails

/**
 * Created by Ajay K K on 2020-02-17.
 */
interface UploadeddocView {
    fun showProgress()
    fun hideProgress()
    fun showChapterContent(data: List<Uploadeddetails>?)
    fun showChapterContentError(msg: String)

    fun deleteError(msg: Any?)
    fun delSuccess(msg: Any?)

    fun fildownsuccess(intent: Intent)
    fun filedownerror()

    fun apiError()
}