package com.burgeon.parentapp.SubjectDetails

import com.app.yashqualitytesting.Datamodels.LoginDatamodel
import com.study.firebasecrash.Retrofit.ApiClient
import com.study.firebasecrash.Retrofit.ApiClientImage
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


/**
 * Created by Ajay K K on 2020-02-17.
 */
class SubjectDetailsInteractor {

    interface onStudentprofileListener {
        fun onSuccess(child: ResponseBody?)
        fun onFailure()

        fun onQuestionSuccess(msg: Any?)
        fun onQuestionFailure(msg: Any?)
    }

    fun getSubjectData(fileurl: String, listener: onStudentprofileListener) {

        /* val downloadService: FileDownloadService = ServiceGenerator.createService(
             FileDownloadService::class.java
         )*/

        val call: Call<ResponseBody> =
            ApiClientImage.getClient.downloadFileWithDynamicUrlSync(fileurl)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                listener?.onFailure()
            }

            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    listener?.onSuccess(response.body())
                } else {
                    listener?.onFailure()
                }
            }

        });
    }

    fun setQuestion(
        userId: String, contentId: String, comment: String,
        listener: onStudentprofileListener
    ) {
        val call: Call<LoginDatamodel> =
            ApiClient.getClient.Question(userId, contentId, comment)
        call.enqueue(object : Callback<LoginDatamodel> {
            override fun onFailure(call: Call<LoginDatamodel>, t: Throwable) {
                listener?.onQuestionFailure("There is some problem.Tray again")
            }

            override fun onResponse(
                call: Call<LoginDatamodel>,
                response: Response<LoginDatamodel>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.status!!)
                        listener?.onQuestionSuccess(response.body()?.msg)
                    else
                        listener?.onQuestionFailure(response.body()?.msg)
                } else {
                    listener?.onQuestionFailure("There is some problem.Tray again")
                }
            }
        });
    }

    fun sendAudio(
        userId: String, contentId: String, audiofilepath: String,
        listener: onStudentprofileListener
    ) {

        val builder1 = MultipartBody.Builder()
        builder1.setType(MultipartBody.FORM)
        builder1.addFormDataPart("student_id", userId)
        builder1.addFormDataPart("content_id", contentId)


        try {
            val file = File(audiofilepath);
            builder1.addFormDataPart(
                "audio",
                file.getName(),
                RequestBody.create(MediaType.parse("multipart/form-data"), file)
            );
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val requestBody = builder1.build()
        val call: Call<LoginDatamodel> = ApiClient.getClient.sendCommentAudio(requestBody)
        call.enqueue(object : Callback<LoginDatamodel> {
            override fun onFailure(call: Call<LoginDatamodel>, t: Throwable) {
                t.printStackTrace()
                listener?.onQuestionFailure("There is some problem.Tray again")
            }

            override fun onResponse(
                call: Call<LoginDatamodel>,
                response: Response<LoginDatamodel>
            ) {
                if (response?.isSuccessful!!) {
                    if (response.body()?.status!!) {
                        listener?.onQuestionSuccess(response.body()?.msg)
                    } else {
                        listener?.onQuestionFailure(response.body()?.msg)
                    }
                } else {
                    listener?.onQuestionFailure("There is some problem.Tray again")
                }
            }
        })

    }

}