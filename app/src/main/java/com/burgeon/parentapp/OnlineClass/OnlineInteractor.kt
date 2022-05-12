package com.burgeon.parentapp.OnlineClass

import com.burgeon.parentapp.Datamodels.MyConference
import com.burgeon.parentapp.Datamodels.OnlineClassPojo
import com.study.firebasecrash.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ajay K K on 2020-02-14.
 */
class OnlineInteractor {

    interface onProfileListener {
        fun onFailure()
         fun onSuccess(conferences: List<MyConference>)
        // fun onSuccess()
    }

    fun getData(
        userId: String?,
        profilePresenter: OnlinePresenter
    ) {

        val call: Call<OnlineClassPojo> = ApiClient.getClient.loadgmeet(userId!!)
        call.enqueue(object : Callback<OnlineClassPojo> {
            override fun onFailure(call: Call<OnlineClassPojo>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<OnlineClassPojo>,
                response: Response<OnlineClassPojo>
            ) {
                if (response.isSuccessful) {
                    if (response?.body()?.data?.conferences?.size!!>0) {
                        profilePresenter.onSuccess(response?.body()?.data?.conferences!!)
                    }
                }
            }
        })

    }
}