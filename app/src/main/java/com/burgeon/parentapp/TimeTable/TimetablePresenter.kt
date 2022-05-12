package com.burgeon.parentapp.TimeTable

import com.burgeon.parentapp.Datamodels.TimetableDatamodel

/**
 * Created by Ajay K K on 2020-02-13.
 */
class TimetablePresenter(
    var timetableview: TimeTableView,
    var timetableinteractor: TimetableInteractor
) : TimetableInteractor.onTimetableListener {

    override fun onSuccess(child: TimetableDatamodel) {
        timetableview?.apply {
            setTimeTableData(child)
            hideProgress()
        }
    }

    override fun onFailure() {
        timetableview?.apply {
            apiError()
            hideProgress()
        }
    }

    fun getTimetable(studentId: String) {
        timetableview?.showProgress()
        timetableinteractor?.getTimeTableData(studentId,this)

    }
}