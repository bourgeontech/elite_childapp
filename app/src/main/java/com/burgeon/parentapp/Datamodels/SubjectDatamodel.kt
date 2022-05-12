package com.burgeon.parentapp.Datamodels

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




/**
 * Created by Ajay K K on 27/06/20.
 */
class SubjectDatamodel {
    @SerializedName("status")
    @Expose
     val status: Boolean? = null
    @SerializedName("data")
    @Expose
     val data: List<SubjectData>? = null
    @SerializedName("msg")
    @Expose
     val msg: String? = null
}

class SubjectData{
    @SerializedName("id")
    @Expose
     val id: String? = null
    @SerializedName("name")
    @Expose
     val name: String? = null
    @SerializedName("type")
    @Expose
     val type: String? = null
}