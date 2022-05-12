package com.burgeon.parentapp.TimeTable

import com.burgeon.parentapp.Datamodels.TimetableDatamodel
import com.study.firebasecrash.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ajay K K on 2020-02-13.
 */
class TimetableInteractor {

    interface onTimetableListener {
        fun onSuccess(child: TimetableDatamodel)
        fun onFailure()
    }

    fun getTimeTableData(
        studentId: String,
        timetablePresenter: TimetablePresenter
    ) {
        val call: Call<TimetableDatamodel> = ApiClient.getClient.gettimetable(studentId)
        call.enqueue(object : Callback<TimetableDatamodel> {
            override fun onFailure(call: Call<TimetableDatamodel>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<TimetableDatamodel>,
                response: Response<TimetableDatamodel>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        timetablePresenter.onSuccess(response.body()!!)
                    }
                }
            }

        })

    }
}