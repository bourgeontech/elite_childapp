package com.burgeon.parentapp.Datamodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ajay K K on 2020-02-11.
 */
class Circular {

    @SerializedName("status")
    @Expose
    val status: Boolean? = null
    @SerializedName("data")
    @Expose
    val data: List<CircularDetails>? = null
}

class CircularDetails {
    @SerializedName("id")
    @Expose
    val id: String? = null
    @SerializedName("title")
    @Expose
    val title: String? = null
    @SerializedName("date")
    @Expose
    val date: String? = null
    @SerializedName("note")
    @Expose
    val note: String? = null
    @SerializedName("type")
    @Expose
    val type: String? = null
    @SerializedName("file")
    @Expose
    val file: String? = null
}