package com.burgeon.parentapp.Uploadeddoc

import android.content.Intent
import com.burgeon.parentapp.Datamodels.Uploadeddetails

/**
 * Created by Ajay K K on 2020-02-17.
 */
class UploadeddocPresenter(
    var docview: UploadeddocView,
    var docInteractor: UploadeddocInteractor
) : UploadeddocInteractor.onStudentprofileListener {


    override fun chapterContent(data: List<Uploadeddetails>?) {
        docview?.apply {
            hideProgress()
            showChapterContent(data)
        }
    }

    override fun chapterContentError(msg: String) {
        docview?.apply {
            hideProgress()
            showChapterContentError(msg)
        }
    }

    override fun deleteSuccess(msg: Any?) {
        docview?.apply {
            hideProgress()
            delSuccess(msg)
        }
    }

    override fun delError(msg: Any?) {
        docview?.apply {
            hideProgress()
            deleteError(msg)
        }
    }

    override fun fileDownload(intent: Intent) {
        docview?.apply {
            hideProgress()
            fildownsuccess(intent)
        }
    }

    override fun fileDownloaderror() {
        docview?.apply {
            hideProgress()
            filedownerror()
        }
    }

    fun getDoc(student_id: String, homework_id: String, type: String) {
        docview.showProgress()
        docInteractor.gteDoc(student_id, homework_id, type, this)
    }

    fun deleteDoc(student_id: String, homework_id: String, upload_id: String) {
        docview.showProgress()
        docInteractor.removeDoc(student_id, homework_id, upload_id, this)
    }

    fun downloadfile(file: String) {
        docview.showProgress()
        docInteractor.downloadFile(file, this)
    }
}