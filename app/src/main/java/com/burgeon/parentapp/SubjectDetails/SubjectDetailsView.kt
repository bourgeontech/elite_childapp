package com.burgeon.parentapp.SubjectDetails

import okhttp3.ResponseBody

/**
 * Created by Ajay K K on 2020-02-17.
 */
interface SubjectDetailsView {
    fun showProgress()
    fun hideProgress()
    fun setSubjectData(child: ResponseBody?)
    fun error()

    fun questionSuccess(msg: Any?)
    fun questioError(msg: Any?)
}