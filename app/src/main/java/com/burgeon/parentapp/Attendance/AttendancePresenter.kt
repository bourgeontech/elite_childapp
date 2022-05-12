package com.burgeon.parentapp.Attendance

import com.burgeon.parentapp.Datamodels.Attendancedetails

/**
 * Created by Ajay K K on 2020-02-14.
 */
class AttendancePresenter(
    var attendanceview: AttendanceView, var attendanceinteractor: AttendanceInteractor
) : AttendanceInteractor.onAttendanceListener {


    override fun onSuccess(data: List<Attendancedetails>?) {
        attendanceview?.apply {
            setAttendanceData(data)
            hideProgress()
        }
    }

    override fun onFailure() {
        attendanceview?.apply {
            apiError()
            hideProgress()
        }
    }

    fun getHomedata(userId: String?, year: String?, month: String?) {
        attendanceview?.showProgress()
        attendanceinteractor.getDashboardData(userId!!, year!!, month!!, this)
    }
}