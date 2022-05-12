package com.burgeon.parentapp.SubjectList

import com.burgeon.parentapp.Datamodels.Chapter
import com.burgeon.parentapp.Datamodels.SubjectData
import com.burgeon.parentapp.Datamodels.contentDetails

/**
 * Created by Ajay K K on 2020-02-17.
 */
interface LessonListView {
    fun showProgress()
    fun hideProgress()
    fun setSubjectData(child: List<SubjectData>?)
    fun setChapterData(child: List<Chapter>?)
    fun setChapterDataError(msg: String?)
    fun showChapterContent(data: List<contentDetails>?)
    fun showChapterContentError(msg: String)
    fun apiError()
}