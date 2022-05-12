package com.burgeon.parentapp.TimeTable

import com.burgeon.parentapp.Datamodels.TimetableDatamodel

/**
 * Created by Ajay K K on 2020-02-13.
 */
interface TimeTableView {

    fun showProgress()
    fun hideProgress()
    fun setTimeTableData(child: TimetableDatamodel)
    fun apiError()
}