package com.burgeon.parentapp.Datamodels

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Replaymain {
    @SerializedName("status")
    @Expose
     val status: Boolean? = null

    @SerializedName("count")
    @Expose
     val count: Int? = null

    @SerializedName("data")
    @Expose
     val data: List<ReplayDetails>? = null

    @SerializedName("msg")
    @Expose
     val msg: String? = null
}

class ReplayDetails{
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

    @SerializedName("question_id")
    @Expose
     val questionId: String? = null

    @SerializedName("teacher_id")
    @Expose
     val teacherId: String? = null

    @SerializedName("name")
    @Expose
     val name: String? = null

    @SerializedName("surname")
    @Expose
     val surname: String? = null
}