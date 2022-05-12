package com.burgeon.parentapp.Remark

import com.burgeon.parentapp.Datamodels.RemarkDetails
import com.burgeon.parentapp.Datamodels.RemarkModel
import com.study.firebasecrash.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemarkInteractor {
    interface GetExaminationPresenter{
        fun onSuccess(id: List<RemarkDetails>?)
        fun onFailure()
       fun  onDeleteSuccess(message:String)
       fun  onDeleteFail(message:String)
    }
    fun exam(
        userId: String?,
        listener: GetExaminationPresenter
    ) {
        val call: Call<RemarkModel> = ApiClient.getClient.parentViewRemark(userId!!)
        call.enqueue(object : Callback<RemarkModel> {
            override fun onFailure(call: Call<RemarkModel>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<RemarkModel>,
                response: Response<RemarkModel>
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

    fun delete(
        userId: String?,
        listener: GetExaminationPresenter
    ) {
        val call: Call<RemarkModel> = ApiClient.getClient.deleteRemark(userId!!)
        call.enqueue(object : Callback<RemarkModel> {
            override fun onFailure(call: Call<RemarkModel>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<RemarkModel>,
                response: Response<RemarkModel>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {

                        listener.onDeleteSuccess(response?.body()?.msg!!.toString())
                    } else {
                        listener.onDeleteFail(response?.body()?.msg!!.toString())
                    }
                } else {
                    listener.onFailure()
                }

            }
        })
    }
}