package com.burgeon.parentapp.Datamodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class OnlineClassPojo {
    @SerializedName("status")
    @Expose
     val status: Boolean? = false

    @SerializedName("data")
    @Expose
     val data: Data? = null

    @SerializedName("msg")
    @Expose
     val msg: Any? = null
}


class Data  {
    @SerializedName("conferences")
    @Expose
     val conferences: List<MyConference>? = null
}

class MyConference {
    @SerializedName("id")
    @Expose
     val id: String? = null

    @SerializedName("purpose")
    @Expose
     val purpose: String? = null

    @SerializedName("staff_id")
    @Expose
     val staffId: String? = null

    @SerializedName("created_id")
    @Expose
     val createdId: String? = null

    @SerializedName("title")
    @Expose
     val title: String? = null

    @SerializedName("date")
    @Expose
     val date: String? = null

    @SerializedName("type")
    @Expose
     val type: String? = null

    @SerializedName("api_data")
    @Expose
     val apiData: Any? = null

    @SerializedName("duration")
    @Expose
     val duration: String? = null

    @SerializedName("subject")
    @Expose
     val subject: Any? = null

    @SerializedName("url")
    @Expose
     val url: String? = null

    @SerializedName("session_id")
    @Expose
     val sessionId: String? = null

    @SerializedName("description")
    @Expose
     val description: String? = null

    @SerializedName("timezone")
    @Expose
     val timezone: String? = null

    @SerializedName("status")
    @Expose
     val status: String? = null

    @SerializedName("created_at")
    @Expose
     val createdAt: String? = null

    @SerializedName("class")
    @Expose
     val _class: String? = null

    @SerializedName("section")
    @Expose
     val section: String? = null

    @SerializedName("create_for_name")
    @Expose
     val createForName: String? = null

    @SerializedName("create_for_surname")
    @Expose
     val createForSurname: String? = null

    @SerializedName("for_create_employee_id")
    @Expose
     val forCreateEmployeeId: String? = null

    @SerializedName("for_create_role_name")
    @Expose
     val forCreateRoleName: String? = null
}
