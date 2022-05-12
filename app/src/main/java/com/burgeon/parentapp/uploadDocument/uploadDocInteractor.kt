package com.burgeon.parentapp.SubjectList

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
class uploadDocInteractor {

    interface onStudentprofileListener {
        fun submitSuccess();
        fun submitError(s: String);
    }

    fun submitData(
        homework_id: String,
        studentId: String,
        title: String,
        remark: String,
        docPaths: ArrayList<String>,
        clicked: Boolean,
        type: String?,
        listener: onStudentprofileListener
    ) {
        if (title == null || title == "")
            listener.submitError("Please enter title")
        else if (docPaths.size == 0)
            listener.submitError("Please select a document")
        else {
            val builder1 = MultipartBody.Builder()
            builder1.setType(MultipartBody.FORM)
            builder1.addFormDataPart("homework_id", homework_id)
            builder1.addFormDataPart("title", title)
            builder1.addFormDataPart("remark", remark)
            print("1..ddd.."+homework_id)
            print("3..ddd.."+remark)
            print("2...ddd.."+title)
            builder1.addFormDataPart("student_id", studentId)

            if (type.equals("exam")) {
                builder1.addFormDataPart("type", "1")
                if (clicked)
                    builder1.addFormDataPart("completed_status", "1")
                else
                    builder1.addFormDataPart("completed_status", "0")
            } else {
                builder1.addFormDataPart("type", "0")
                builder1.addFormDataPart("completed_status", "0")

            }

            try {
                for (i in 0 until docPaths.size) {
                    Log.e("doc url.............", docPaths[i].toString())
                    val file = File(docPaths.get(0));
                    builder1.addFormDataPart(
                        "document",
                        file.getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"), file)
                    );

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            val requestBody = builder1.build()
            print("ddd.."+homework_id)
            print("ddd.."+remark)
            print("ddd.."+title)
            val call: Call<LoginDatamodel> = ApiClient.getClient.studentSubmitHomework(requestBody)
            call.enqueue(object : Callback<LoginDatamodel> {
                override fun onFailure(call: Call<LoginDatamodel>, t: Throwable) {
                    listener.submitError("There is some problem")
                }

                override fun onResponse(
                    call: Call<LoginDatamodel>,
                    response: Response<LoginDatamodel>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()?.status!!) {
                            listener.submitSuccess()
                        } else {
                            listener.submitError("There is some problem")
                        }
                    }
                }
            })
        }
    }

}