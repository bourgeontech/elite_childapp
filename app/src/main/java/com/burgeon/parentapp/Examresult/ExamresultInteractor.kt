package com.burgeon.parentapp.Examresult

import com.burgeon.parentapp.Datamodels.ExamresultDatamodel
import com.burgeon.parentapp.Datamodels.ExamresultdetailsDatamodel
import com.study.firebasecrash.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ajay K K on 2020-02-13.
 */
class ExamresultInteractor {

    interface onExamresultlistener {
        fun onSuccess(data: List<ExamresultdetailsDatamodel>?)
        fun onFailure()
    }


    fun getExamresult(parentId: String, listener: onExamresultlistener) {

        val call: Call<ExamresultDatamodel> = ApiClient.getClient.getExamResult(parentId)
        call.enqueue(object : Callback<ExamresultDatamodel> {
            override fun onFailure(call: Call<ExamresultDatamodel>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ExamresultDatamodel>,
                response: Response<ExamresultDatamodel>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onSuccess(response.body()?.data)
                    } else {
                        listener.onFailure()
                    }
                } else {
                    listener.onFailure()
                }
            }

        })

    }

}