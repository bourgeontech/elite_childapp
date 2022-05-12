package com.burgeon.parentapp.Datamodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ajay K K on 2020-02-13.
 */
class TimetableDatamodel {
    @SerializedName("status")
    @Expose
    val status: Boolean? = null
    @SerializedName("monday")
    @Expose
    val monday: List<Monday>? = null
    @SerializedName("tuesday")
    @Expose
    val tuesday: List<Tuesday>? = null
    @SerializedName("wednesday")
    @Expose
    val wednesday: List<Wednesday>? = null
    @SerializedName("thursday")
    @Expose
    val thursday: List<Thursday>? = null
    @SerializedName("friday")
    @Expose
    val friday: List<Friday>? = null
    @SerializedName("saturday")
    @Expose
    val saturday: List<Saturday>? = null
    @SerializedName("sunday")
    @Expose
    val sunday: List<Sunday>? = null
}

class Monday {
    @SerializedName("day_name")
    @Expose
    val dayName: String? = null
    @SerializedName("start_time")
    @Expose
    val startTime: String? = null
    @SerializedName("end_time")
    @Expose
    val endTime: String? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
}

class Tuesday {
    @SerializedName("day_name")
    @Expose
    val dayName: String? = null
    @SerializedName("start_time")
    @Expose
    val startTime: String? = null
    @SerializedName("end_time")
    @Expose
    val endTime: String? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
}

class Wednesday {
    @SerializedName("day_name")
    @Expose
    val dayName: String? = null
    @SerializedName("start_time")
    @Expose
    val startTime: String? = null
    @SerializedName("end_time")
    @Expose
    val endTime: String? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
}

class Thursday {
    @SerializedName("day_name")
    @Expose
    val dayName: String? = null
    @SerializedName("start_time")
    @Expose
    val startTime: String? = null
    @SerializedName("end_time")
    @Expose
    val endTime: String? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
}

class Friday {
    @SerializedName("day_name")
    @Expose
    val dayName: String? = null
    @SerializedName("start_time")
    @Expose
    val startTime: String? = null
    @SerializedName("end_time")
    @Expose
    val endTime: String? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
}

class Saturday {
    @SerializedName("day_name")
    @Expose
    val dayName: String? = null
    @SerializedName("start_time")
    @Expose
    val startTime: String? = null
    @SerializedName("end_time")
    @Expose
    val endTime: String? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
}

class Sunday {
    @SerializedName("day_name")
    @Expose
    val dayName: String? = null
    @SerializedName("start_time")
    @Expose
    val startTime: String? = null
    @SerializedName("end_time")
    @Expose
    val endTime: String? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
}