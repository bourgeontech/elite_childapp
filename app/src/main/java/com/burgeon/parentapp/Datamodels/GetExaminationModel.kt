package com.burgeon.parentapp.Datamodels

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName





class GetExaminationModel {
    @SerializedName("status")
    @Expose
     val status: Boolean? = null
    @SerializedName("data")
    @Expose
     val data: List<GetExaminationList>? = null
    @SerializedName("msg")
    @Expose
     val msg: Any? = null
}

class GetExaminationList {
    @SerializedName("id")
    @Expose
     val id: String? = null
    @SerializedName("name")
    @Expose
     val name: String? = null
}
