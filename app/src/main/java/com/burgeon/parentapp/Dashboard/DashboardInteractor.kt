package com.burgeon.parentapp.Dashboard

import com.app.yashqualitytesting.Datamodels.LoginDatamodel
import com.study.firebasecrash.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ajay K K on 2020-02-12.
 */
class DashboardInteractor {

    interface onHomeListener {
        fun onSuccess()
        fun onFailure()

        fun onSignedSuccess()
        fun onSignedFailure()
    }

  /*  fun getDashboardData(parentId: String, listener: onHomeListener) {
        val call: Call<ChildDatamodel> = ApiClient.getClient.getStudentByParent(parentId)
        call.enqueue(object : Callback<ChildDatamodel> {
            override fun onFailure(call: Call<ChildDatamodel>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ChildDatamodel>,
                response: Response<ChildDatamodel>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onSuccess(response.body()?.child)
                    }
                }
            }

        });
    }
*/

    fun addSignInData(childId: String, listener: onHomeListener) {
        val call: Call<LoginDatamodel> = ApiClient.getClient.signIn(childId)
        call.enqueue(object : Callback<LoginDatamodel> {
            override fun onFailure(call: Call<LoginDatamodel>, t: Throwable) {
                listener.onSuccess()
            }

            override fun onResponse(
                call: Call<LoginDatamodel>,
                response: Response<LoginDatamodel>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onSuccess()
                    }else{
                        listener.onSuccess()
                    }
                }
            }

        });
    }

    fun getSignInData(childId: String, listener: onHomeListener) {
        val call: Call<LoginDatamodel> = ApiClient.getClient.checkIsSignIn(childId)
        call.enqueue(object : Callback<LoginDatamodel> {
            override fun onFailure(call: Call<LoginDatamodel>, t: Throwable) {
                listener.onSignedFailure();
            }

            override fun onResponse(
                call: Call<LoginDatamodel>,
                response: Response<LoginDatamodel>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onSignedSuccess()
                    }else{
                        listener.onSignedFailure();
                    }
                }
            }

        });
    }

}