package com.burgeon.parentapp.Login

import com.app.yashqualitytesting.Datamodels.LoginDatamodel
import com.study.firebasecrash.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ajay K K on 2020-01-08.
 */
class LoginInteractor {

    private var studentSchool: String = ""
    private var studentSectionid: String = ""
    private var studentSection: String = ""
    private var studentClassid: String = ""
    private var studentClass: String = ""
    private var parentName: String = ""
    private var parentId: String = ""
    private var studentImage: String = ""

    interface OnLoginFinishedListener {
        fun onUsernameError()
        fun onPasswordError()
        fun onSuccess(
            id: String,
            name: String,
            mobile: String
        )

        fun onFailure(msg: String?)
    }

    fun login(username: String, password: String, listener: OnLoginFinishedListener) {

        if (username.length == 0)
            listener.onUsernameError()
        else if (password.length == 0)
            listener.onPasswordError()
        else {

            val call: Call<LoginDatamodel> = ApiClient.getClient.userLogin(username, password)
            call.enqueue(object : Callback<LoginDatamodel> {
                override fun onFailure(call: Call<LoginDatamodel>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<LoginDatamodel>,
                    response: Response<LoginDatamodel>
                ) {

                    // listener.onFailure("works1.............")
                    if (response.isSuccessful) {

                        // listener.onFailure("works2.............")

                        if (response.body()?.status!!) {

                            var parentId = response.body()?.parent?.parentId!!
                            var parentName = response.body()?.parent?.name!!
                            var parentMobile = response.body()?.parent?.mobileno!!


                           /* if (response.body()?.parent?.parentId != null)
                                parentId = response.body()?.parent?.parentId!!
                            if (response.body()?.parent?.name != null)
                                parentName = response.body()?.parent?.name!!
                            if (response.body()?.child?.name != null)
                                studentClass = response.body()?.child?._class!!
                            if (response.body()?.child?.name != null)
                                studentClassid = response.body()?.child?.class_id!!
                            if (response.body()?.child?.name != null)
                                studentSection = response.body()?.child?.section!!

                            if (response.body()?.child?.name != null)
                                studentSectionid = response.body()?.child?.section_id!!
                            if (response.body()?.child?.name != null)
                                studentSchool = response.body()?.child?.sch_name!!
                            if (response.body()?.child?.image != null)
                                studentImage = response.body()?.child?.image!!*/



                            listener.onSuccess(
                                parentId,
                                parentName,
                                parentMobile
                            )
                        } else {
                            listener.onFailure(response?.body()?.msg as String?)
                        }
                    } else {
                        listener.onFailure("There is some problem.Pleaase try later")
                    }

                }
            })
        }
    }
}


