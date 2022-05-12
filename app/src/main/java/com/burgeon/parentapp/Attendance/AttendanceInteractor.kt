package com.burgeon.parentapp.Attendance

import com.burgeon.parentapp.Datamodels.AttendanceDatamodel
import com.burgeon.parentapp.Datamodels.Attendancedetails
import com.study.firebasecrash.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ajay K K on 2020-02-14.
 */
class AttendanceInteractor {

    interface onAttendanceListener {
        fun onSuccess(data: List<Attendancedetails>?)
        fun onFailure()
    }

    fun getDashboardData(
        studentId: String,
        year: String,
        month: String,
        listener: onAttendanceListener
    ) {
        val call: Call<AttendanceDatamodel> = ApiClient.getClient.parentViewAttandence(studentId,year,month)
        call.enqueue(object : Callback<AttendanceDatamodel> {
            override fun onFailure(call: Call<AttendanceDatamodel>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<AttendanceDatamodel>,
                response: Response<AttendanceDatamodel>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onSuccess(response?.body()?.data)
                    }else{
                        listener.onFailure();
                    }
                }
            }

        });
    }

}