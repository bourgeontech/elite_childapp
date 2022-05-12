package com.burgeon.parentapp.Datamodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Ajay K K on 2020-02-14.
 */
class AttendanceDatamodel {
    @SerializedName("status")
    @Expose
     val status: Boolean? = null
    @SerializedName("data")
    @Expose
     val data: List<Attendancedetails>? = null
    @SerializedName("msg")
    @Expose
     val msg: Any? = null
}

class Attendancedetails{
    @SerializedName("type")
    @Expose
     val type: String? = null
    @SerializedName("date")
    @Expose
     val date: String? = null
}