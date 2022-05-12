package com.burgeon.parentapp.Datamodels

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ExamDetails {
    @SerializedName("status")
    @Expose
     val status: Boolean? = null
    @SerializedName("data")
    @Expose
     val data: List<ExamDetailsList>? = null
    @SerializedName("msg")
    @Expose
     val msg: Any? = null
}

class ExamDetailsList {
    @SerializedName("date_of_exam")
    @Expose
     val dateOfExam: String? = null
    @SerializedName("subject")
    @Expose
     val subject: String? = null
    @SerializedName("duration")
    @Expose
     val duration: String? = null
}
