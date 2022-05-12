package com.app.yashqualitytesting.Datamodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class LoginDatamodel {

    @SerializedName("status")
    @Expose
    val status: Boolean? = null
    @SerializedName("parent")
    @Expose
    val parent: Parent? = null
    @SerializedName("child")
    @Expose
    val child: List<Child>? = null
    @SerializedName("msg")
    @Expose
    val msg: Any? = null
}

class Parent {
    @SerializedName("parent_id")
    @Expose
    val parentId: String? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
    @SerializedName("relation")
    @Expose
    val relation: String? = null
    @SerializedName("mobileno")
    @Expose
    val mobileno: String? = null
    @SerializedName("email")
    @Expose
    val email: String? = null
    @SerializedName("address")
    @Expose
    val address: String? = null
}

class Child {
    @SerializedName("id")
    @Expose
    val id: String? = null
    @SerializedName("admission_no")
    @Expose
    val admissionNo: String? = null
    @SerializedName("roll_no")
    @Expose
    val rollNo: String? = null
    @SerializedName("firstname")
    @Expose
    val firstname: String? = null
    @SerializedName("class")
    @Expose
    val _class: String? = null
    @SerializedName("section")
    @Expose
    val section: String? = null
}
