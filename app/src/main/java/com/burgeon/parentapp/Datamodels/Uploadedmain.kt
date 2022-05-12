package com.burgeon.parentapp.Datamodels

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Uploadedmain {
    @SerializedName("status")
    @Expose
     val status: Boolean? = null

    @SerializedName("count")
    @Expose
     val count: Int? = null

    @SerializedName("data")
    @Expose
     val data: List<Uploadeddetails>? = null

    @SerializedName("msg")
    @Expose
     val msg: String? = null

}

class Uploadeddetails{
    @SerializedName("id")
    @Expose
     val id: String? = null

    @SerializedName("title")
    @Expose
     val title: String? = null

    @SerializedName("remark")
    @Expose
     val remark: String? = null

    @SerializedName("document")
    @Expose
     val document: String? = null

    @SerializedName("date")
    @Expose
     val date: String? = null


}