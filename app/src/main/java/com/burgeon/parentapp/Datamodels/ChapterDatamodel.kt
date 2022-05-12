package com.burgeon.parentapp.Datamodels

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




/**
 * Created by Ajay K K on 27/06/20.
 */
class ChapterDatamodel {
    @SerializedName("status")
    @Expose
     val status: Boolean? = null
    @SerializedName("data")
    @Expose
     val data: List<Chapter>? = null
    @SerializedName("msg")
    @Expose
     val msg: String? = null

}

class Chapter{
    @SerializedName("id")
    @Expose
     val id: String? = null
    @SerializedName("class_id")
    @Expose
     val classId: String? = null
    @SerializedName("subject_id")
    @Expose
     val subjectId: String? = null
    @SerializedName("unit_no")
    @Expose
     val unitNo: String? = null
    @SerializedName("unit_name")
    @Expose
     val unitName: String? = null

}