package com.burgeon.parentapp.SubjectList

import com.burgeon.parentapp.Datamodels.Chapter
import com.burgeon.parentapp.Datamodels.SubjectData
import com.burgeon.parentapp.Datamodels.contentDetails

/**
 * Created by Ajay K K on 2020-02-17.
 */
class LessonListPresenter(
    var studentprofileview: LessonListView,
    var studentprofileInteractor: LessonListInteractor
) : LessonListInteractor.onStudentprofileListener {

    override fun onSuccess(child: List<SubjectData>?) {
        studentprofileview?.apply {
            hideProgress()
            setSubjectData(child)
        }
    }

    override fun onFailure() {
        studentprofileview?.apply {
            hideProgress()
            apiError()
        }
    }

    override fun chapterData(data: List<Chapter>?) {
        studentprofileview?.apply {
            hideProgress()
            setChapterData(data)
        }
    }

    override fun chapterDataError(msg: String?) {
        studentprofileview?.apply {
            hideProgress()
            setChapterDataError(msg)
        }
    }

    override fun chapterContent(data: List<contentDetails>?) {
        studentprofileview?.apply {
            hideProgress()
            showChapterContent(data)
        }
    }

    override fun chapterContentError(msg: String) {
        studentprofileview?.apply {
            hideProgress()
            showChapterContentError(msg)
        }
    }

    fun getSubject(studentId: String) {
        studentprofileview?.showProgress()
        studentprofileInteractor.getSubjectData(studentId, this)

    }

    fun getChapter(studentId: String) {
        studentprofileview?.showProgress()
        studentprofileInteractor.getChapterData(studentId, this)
    }

    fun getChapterContent(chapter_id:String){
        studentprofileview?.showProgress()
        studentprofileInteractor.getChaptercontent(chapter_id, this)
    }
}