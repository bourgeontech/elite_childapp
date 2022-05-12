package com.burgeon.parentapp.Changepassword

import com.app.yashqualitytesting.Datamodels.LoginDatamodel
import com.study.firebasecrash.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ajay K K on 2020-02-24.
 */
class ChangepasswdInteractor {

    interface onPasswdchangedListere {
        fun onOldPasswderror()
        fun onNewPasswderror()
        fun onPasswdmismatcherror()
        fun onSuccess()
        fun onFailure(msg: String?)
    }

    fun changePasswd(
        id: String,
        oldpasswd: String,
        newpasswd: String,
        repasswd: String,
        listener: onPasswdchangedListere
    ) {

        if (oldpasswd.length == 0)
            listener.onOldPasswderror()
        else if (newpasswd.length == 0)
            listener.onNewPasswderror()
        else if (!newpasswd.equals(repasswd))
            listener.onPasswdmismatcherror()
        else {

            val call: Call<LoginDatamodel> =
                ApiClient.getClient.changepass(oldpasswd, newpasswd, id);
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
                            listener.onFailure(response.body()?.msg as String?)
                        }
                    } else {
                        listener.onFailure("There is some problem.please try later")
                    }

                }
            })
        }
    }
}