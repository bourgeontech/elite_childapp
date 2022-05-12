package com.burgeon.parentapp.Datamodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ajay K K on 2020-02-12.
 */
class ChildDatamodel {

    @SerializedName("status")
    @Expose
    val status: Boolean? = null
    @SerializedName("data")
    @Expose
    val child: List<childdetailsDatamodel>? = null

}

class childdetailsDatamodel {
    @SerializedName("id")
    @Expose
    val id: String? = ""
    @SerializedName("admission_no")
    @Expose
    val admissionNo: String? = ""
    @SerializedName("roll_no")
    @Expose
    val rollNo: String? = ""
    @SerializedName("firstname")
    @Expose
    val firstname: String? = ""
    @SerializedName("class")
    @Expose
    val _class: String? = ""
    @SerializedName("class_id")
    @Expose
    val classId: String? = ""
    @SerializedName("section")
    @Expose
    val section: String? = ""
    @SerializedName("section_id")
    @Expose
    val sectionId: String? = ""
    @SerializedName("teacher_name")
    @Expose
    val teachername: String? = ""
    @SerializedName("teacher_phone")
    @Expose
    val teacherphone: String? = ""
    @SerializedName("teacher_email")
    @Expose
    val teacheremail: String? = ""

    @SerializedName("teacher_id")
    @Expose
    val teacher_id: String? = ""

}