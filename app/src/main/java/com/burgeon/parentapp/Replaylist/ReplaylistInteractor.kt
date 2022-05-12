package com.burgeon.parentapp.Replaylist

import com.burgeon.parentapp.Datamodels.Replaymain
import com.study.firebasecrash.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ajay K K on 2020-02-17.
 */
class ReplaylistInteractor {

    interface onStudentprofileListener {
        fun onSuccess(child: Replaymain?)
        fun onFailure(msg: String?)
    }

    fun getStudentprofileData(parentId: String, listener: onStudentprofileListener) {
        val call: Call<Replaymain> = ApiClient.getClient.viewTeacherReply(parentId)
        call.enqueue(object : Callback<Replaymain> {
            override fun onFailure(call: Call<Replaymain>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<Replaymain>,
                response: Response<Replaymain>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onSuccess(response.body())
                    } else {
                        listener.onFailure(response?.body()?.msg)
                    }
                }
            }

        });
    }

}