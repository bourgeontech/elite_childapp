package com.burgeon.parentapp.Datamodels

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class QuestionmainPOjo {
    @SerializedName("status")
    @Expose
     val status: Boolean? = null

    @SerializedName("count")
    @Expose
     val count: Int? = null

    @SerializedName("data")
    @Expose
     val data: List<Questions>? = null

    @SerializedName("msg")
    @Expose
     val msg: String? = null
}

class Questions{
    @SerializedName("id")
    @Expose
     val id: String? = null

    @SerializedName("type")
    @Expose
    val type: String? = null

    @SerializedName("comment")
    @Expose
     val comment: String? = null

    @SerializedName("post_date")
    @Expose
     val postDate: String? = null

    @SerializedName("student_id")
    @Expose
     val studentId: String? = null

    @SerializedName("firstname")
    @Expose
     val firstname: String? = null
}