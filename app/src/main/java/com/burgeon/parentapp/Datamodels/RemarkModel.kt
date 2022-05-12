package com.burgeon.parentapp.Datamodels

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class RemarkModel {
    @SerializedName("status")
    @Expose
     val status: Boolean? = null
    @SerializedName("data")
    @Expose
     val data: List<RemarkDetails>? = null
    @SerializedName("msg")
    @Expose
     val msg: Any? = null
}

class RemarkDetails {
    @SerializedName("date")
    @Expose
     val date: String? = null
    @SerializedName("remark")
    @Expose
     val remark: String? = null

    @SerializedName("id")
    @Expose
    val id: String? = null
}
