package com.burgeon.parentapp.GetExamination

import com.burgeon.parentapp.Datamodels.GetExaminationList
import com.burgeon.parentapp.Datamodels.GetExaminationModel
import com.study.firebasecrash.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExaminationInteractor {

    interface GetExaminationPresenter{
        fun onSuccess(id: List<GetExaminationList>?)
        fun onFailure()

    }
    fun exam(
        userId: String?,
        listener: GetExaminationPresenter
    ) {
        val call: Call<GetExaminationModel> = ApiClient.getClient.getExamination(userId!!)
        call.enqueue(object : Callback<GetExaminationModel> {
            override fun onFailure(call: Call<GetExaminationModel>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<GetExaminationModel>,
                response: Response<GetExaminationModel>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onSuccess(response?.body()?.data!!)
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