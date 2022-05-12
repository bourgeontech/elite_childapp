package com.burgeon.parentapp.Profile

import com.burgeon.parentapp.Datamodels.ChildDatamodel
import com.burgeon.parentapp.Datamodels.childdetailsDatamodel
import com.study.firebasecrash.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ajay K K on 2020-02-14.
 */
class ProfileInteractor {

    interface onProfileListener {
        fun onFailure()
        fun onSuccess(child: List<childdetailsDatamodel>?)
    }

    fun getProfileData(
        userId: String?,
        profilePresenter: ProfilePresenter
    ) {

        val call: Call<ChildDatamodel> = ApiClient.getClient.getStudentByParent(userId!!)
        call.enqueue(object : Callback<ChildDatamodel> {
            override fun onFailure(call: Call<ChildDatamodel>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ChildDatamodel>,
                response: Response<ChildDatamodel>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        profilePresenter.onSuccess(response.body()?.child)
                    }
                }
            }
        })

    }
}