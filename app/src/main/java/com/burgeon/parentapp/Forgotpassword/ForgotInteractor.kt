package com.burgeon.parentapp.Forgotpassword

import com.app.yashqualitytesting.Datamodels.LoginDatamodel
import com.study.firebasecrash.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ajay K K on 2020-01-08.
 */
class ForgotInteractor {

    interface OnForgotFinishedListener {
        fun onUsernameError()
        fun onSuccess()
        fun onFailure()
    }

    fun forgotpasswd(username: String, listener: OnForgotFinishedListener) {

        if (username.length == 0||username.length<10||username.length>13)
            listener.onUsernameError()

        else {

            val call: Call<LoginDatamodel> = ApiClient.getClient.userForgotpasswd(username)
            call.enqueue(object : Callback<LoginDatamodel> {
                override fun onFailure(call: Call<LoginDatamodel>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<LoginDatamodel>,
                    response: Response<LoginDatamodel>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()?.status!!) {
                            listener.onSuccess()
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
}


