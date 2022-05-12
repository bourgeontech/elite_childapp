package com.burgeon.parentapp.Datamodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ajay K K on 2020-02-13.
 */
class ExamresultDatamodel {
    @SerializedName("status")
    @Expose
    val status: Boolean? = null
    @SerializedName("data")
    @Expose
    val data: List<ExamresultdetailsDatamodel>? = null
    @SerializedName("msg")
    @Expose
    val msg: Any? = null
}

class ExamresultdetailsDatamodel {

    var visiblity: Boolean? = false
    @SerializedName("exam_name")
    @Expose
    val examName: String? = null
    @SerializedName("exam_result")
    @Expose
    val examResult: List<ExamResult>? = null
}

class ExamResult {
    @SerializedName("exam_schedule_id")
    @Expose
    val examScheduleId: String? = null
    @SerializedName("subject")
    @Expose
    val subject: String? = null
    @SerializedName("attendence")
    @Expose
    val attendence: String? = null
    @SerializedName("get_marks")
    @Expose
    val getMarks: String? = null
    @SerializedName("result")
    @Expose
    val result: String? = null
    @SerializedName("date_of_exam")
    @Expose
    val date_of_exam: String? = null


}