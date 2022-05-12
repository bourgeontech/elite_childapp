package com.burgeon.parentapp.Attendance

import com.burgeon.parentapp.Datamodels.Attendancedetails

/**
 * Created by Ajay K K on 2020-02-14.
 */
interface AttendanceView {

    fun showProgress()
    fun hideProgress()
    fun setAttendanceData(data: List<Attendancedetails>?)
    fun apiError()
}