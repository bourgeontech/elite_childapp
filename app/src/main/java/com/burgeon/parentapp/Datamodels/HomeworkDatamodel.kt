package com.burgeon.parentapp.Datamodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ajay K K on 2020-02-12.
 */
class HomeworkDatamodel {
    @SerializedName("status")
    @Expose
    val status: Boolean? = null
    @SerializedName("data")
    @Expose
    val data: List<HomeworkDetailsmodel>? = null
    @SerializedName("msg")
    @Expose
    val msg: Any? = null
}

class HomeworkDetailsmodel {
    @SerializedName("id")
    @Expose
    val id: String? = null
    @SerializedName("subject")
    @Expose
    val subject: String? = null
    @SerializedName("teacher")
    @Expose
    val teacher: String? = null
    @SerializedName("homework_date")
    @Expose
    val homeworkDate: String? = null
    @SerializedName("submit_date")
    @Expose
    val submitDate: String? = null
    @SerializedName("description")
    @Expose
    val description: String? = null
    @SerializedName("file_type")
    @Expose
    val fileType: String? = null
    @SerializedName("file")
    @Expose
    val file: String? = null

    @SerializedName("remarks")
    @Expose
    val remarks: String? = null
}