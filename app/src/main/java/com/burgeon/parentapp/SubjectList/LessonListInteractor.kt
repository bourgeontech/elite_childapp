package com.burgeon.parentapp.SubjectList

import com.burgeon.parentapp.Datamodels.*
import com.study.firebasecrash.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ajay K K on 2020-02-17.
 */
class LessonListInteractor {

    interface onStudentprofileListener{
        fun onSuccess(child: List<SubjectData>?)
        fun onFailure()

        fun chapterData(data: List<Chapter>?)
        fun chapterDataError(msg: String?)

        fun chapterContent(data: List<contentDetails>?)
        fun chapterContentError(msg:String)
    }

    fun getSubjectData(parentId: String, listener: onStudentprofileListener) {
        val call: Call<SubjectDatamodel> = ApiClient.getClient.getSubjectsByClassId(parentId)
        call.enqueue(object : Callback<SubjectDatamodel> {
            override fun onFailure(call: Call<SubjectDatamodel>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<SubjectDatamodel>,
                response: Response<SubjectDatamodel>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onSuccess(response.body()?.data)
                    }
                }
            }

        });
    }


    fun getChapterData(parentId: String, listener: onStudentprofileListener) {
        val call: Call<ChapterDatamodel> = ApiClient.getClient.getChaptersBySubject(parentId)
        call.enqueue(object : Callback<ChapterDatamodel> {
            override fun onFailure(call: Call<ChapterDatamodel>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ChapterDatamodel>,
                response: Response<ChapterDatamodel>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.chapterData(response.body()?.data)
                    }else{
                        listener.chapterDataError(response.body()?.msg)
                    }
                }
            }

        });
    }

    fun getChaptercontent(chapter_id: String, listener: onStudentprofileListener) {
        val call: Call<ChapterContentDatamodel> = ApiClient.getClient.getChapterContent(chapter_id)
        call.enqueue(object : Callback<ChapterContentDatamodel> {
            override fun onFailure(call: Call<ChapterContentDatamodel>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ChapterContentDatamodel>,
                response: Response<ChapterContentDatamodel>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.chapterContent(response.body()?.data)
                    }else{
                        response.body()?.msg?.let { listener.chapterContentError(it) }
                    }
                }
            }

        });
    }

}