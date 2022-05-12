package com.app.khmhsschild.SubjectList

import android.util.Log
import com.app.yashqualitytesting.Datamodels.LoginDatamodel

import com.study.firebasecrash.Retrofit.ApiClient
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

/**
 * Created by Ajay K K on 2020-02-17.
 */
class CommunicationInteractor {

    interface onStudentprofileListener {
        fun submitSuccess();
        fun submitError(s: String);
    }


    fun submitData(
        teacherId: String,
        studentId: String,
        type: String,
        matter: String,
        enquiryPresenter: CommunicationPresenter
    ) {

        val call: Call<LoginDatamodel> = ApiClient.getClient.userCommunicate(studentId,teacherId ,type,matter)
        call.enqueue(object : Callback<LoginDatamodel> {
            override fun onFailure(call: Call<LoginDatamodel>, t: Throwable) {
                t.printStackTrace()
                enquiryPresenter.submitError("There is some problem.Tray again")
            }

            override fun onResponse(
                call: Call<LoginDatamodel>,
                response: Response<LoginDatamodel>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        enquiryPresenter.submitSuccess()
                    } else {
                        enquiryPresenter.submitError(response?.body()?.msg.toString())
                    }
                } else {
                    enquiryPresenter.submitError("There is some problem.Tray again")
                }
            }
        })
    }


}