package com.burgeon.parentapp.ExamDetails

import com.burgeon.parentapp.Datamodels.ExamDetails
import com.burgeon.parentapp.Datamodels.ExamDetailsList
import com.study.firebasecrash.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExamDetailsInteractor {

    interface GetExaminationPresenter{
        fun onSuccess(id: List<ExamDetailsList>?)
        fun onFailure()

    }
    fun examdetails(
        rowId: String,studentId: String,
        listener: GetExaminationPresenter
    ) {
        val call: Call<ExamDetails> = ApiClient.getClient.getExamSchedule(studentId,rowId)
        call.enqueue(object : Callback<ExamDetails> {
            override fun onFailure(call: Call<ExamDetails>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ExamDetails>,
                response: Response<ExamDetails>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onSuccess(response?.body()?.data)
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