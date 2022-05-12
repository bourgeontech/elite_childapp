package com.burgeon.parentapp.Studentprofile

import com.burgeon.parentapp.Datamodels.Studentprofile
import com.burgeon.parentapp.Datamodels.StudentprofileDetails
import com.study.firebasecrash.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ajay K K on 2020-02-17.
 */
class StudentprofileInteractor {

    interface onStudentprofileListener{
        fun onSuccess(child: StudentprofileDetails?)
        fun onFailure()
    }

    fun getStudentprofileData(parentId: String, listener: onStudentprofileListener) {
        val call: Call<Studentprofile> = ApiClient.getClient.studentProfile(parentId)
        call.enqueue(object : Callback<Studentprofile> {
            override fun onFailure(call: Call<Studentprofile>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<Studentprofile>,
                response: Response<Studentprofile>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onSuccess(response.body()?.data)
                    }
                }
            }

        });
    }

}