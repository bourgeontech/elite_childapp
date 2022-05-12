package com.burgeon.parentapp.Datamodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Ajay K K on 2020-02-17.
 */
class Studentprofile {
    @SerializedName("status")
    @Expose
    val status: Boolean? = null
    @SerializedName("data")
    @Expose
    val data: StudentprofileDetails? = null
    @SerializedName("msg")
    @Expose
    val msg: Any? = null
}

class StudentprofileDetails {
    @SerializedName("image")
    @Expose
    val image: Any? = null
    @SerializedName("firstname")
    @Expose
    val firstname: String? = null
    @SerializedName("lastname")
    @Expose
    val lastname: String? = null
    @SerializedName("roll_no")
    @Expose
    val rollNo: String? = null
    @SerializedName("class")
    @Expose
    val _class: String? = null
    @SerializedName("section")
    @Expose
    val section: String? = null
    @SerializedName("gender")
    @Expose
    val gender: String? = null
    @SerializedName("dob")
    @Expose
    val dob: String? = null
    @SerializedName("blood_group")
    @Expose
    val bloodGroup: String? = null
    @SerializedName("email")
    @Expose
    val email: String? = null
    @SerializedName("house_name")
    @Expose
    val houseName: Any? = null
    @SerializedName("height")
    @Expose
    val height: String? = null
    @SerializedName("weight")
    @Expose
    val weight: String? = null
    @SerializedName("measurement_date")
    @Expose
    val measurementDate: String? = null
    @SerializedName("rte")
    @Expose
    val rte: String? = null
    @SerializedName("admission_no")
    @Expose
    val admissionNo: String? = null
    @SerializedName("admission_date")
    @Expose
    val admissionDate: String? = null
    @SerializedName("current_address")
    @Expose
    val currentAddress: String? = null
    @SerializedName("permanent_address")
    @Expose
    val permanentAddress: String? = null
    @SerializedName("city")
    @Expose
    val city: Any? = null
    @SerializedName("state")
    @Expose
    val state: Any? = null
    @SerializedName("pincode")
    @Expose
    val pincode: Any? = null
    @SerializedName("religion")
    @Expose
    val religion: String? = null
    @SerializedName("cast")
    @Expose
    val cast: String? = null
    @SerializedName("father_name")
    @Expose
    val fatherName: String? = null
    @SerializedName("father_phone")
    @Expose
    val fatherPhone: String? = null
    @SerializedName("father_occupation")
    @Expose
    val fatherOccupation: String? = null
    @SerializedName("mother_name")
    @Expose
    val motherName: String? = null
    @SerializedName("mother_phone")
    @Expose
    val motherPhone: String? = null
    @SerializedName("mother_occupation")
    @Expose
    val motherOccupation: String? = null
    @SerializedName("guardian_name")
    @Expose
    val guardianName: String? = null
    @SerializedName("guardian_relation")
    @Expose
    val guardianRelation: String? = null
    @SerializedName("guardian_phone")
    @Expose
    val guardianPhone: String? = null
    @SerializedName("guardian_occupation")
    @Expose
    val guardianOccupation: String? = null
    @SerializedName("guardian_address")
    @Expose
    val guardianAddress: String? = null
    @SerializedName("guardian_email")
    @Expose
    val guardianEmail: String? = null
    @SerializedName("previous_school")
    @Expose
    val previousSchool: String? = null
    @SerializedName("category_id")
    @Expose
    val categoryId: String? = null
    @SerializedName("adhar_no")
    @Expose
    val adharNo: String? = null
    @SerializedName("bank_account_no")
    @Expose
    val bankAccountNo: String? = null
    @SerializedName("bank_name")
    @Expose
    val bankName: String? = null
    @SerializedName("ifsc_code")
    @Expose
    val ifscCode: String? = null
    @SerializedName("transport_fees")
    @Expose
    val transportFees: String? = null
    @SerializedName("vehroute_id")
    @Expose
    val vehrouteId: String? = null
    @SerializedName("route_id")
    @Expose
    val routeId: Any? = null
    @SerializedName("vehicle_id")
    @Expose
    val vehicleId: Any? = null
    @SerializedName("route_title")
    @Expose
    val routeTitle: Any? = null
    @SerializedName("vehicle_no")
    @Expose
    val vehicleNo: Any? = null
    @SerializedName("room_no")
    @Expose
    val roomNo: Any? = null
    @SerializedName("driver_name")
    @Expose
    val driverName: Any? = null
    @SerializedName("driver_contact")
    @Expose
    val driverContact: Any? = null
    @SerializedName("hostel_id")
    @Expose
    val hostelId: Any? = null
    @SerializedName("hostel_name")
    @Expose
    val hostelName: Any? = null
    @SerializedName("room_type_id")
    @Expose
    val roomTypeId: Any? = null
    @SerializedName("room_type")
    @Expose
    val roomType: Any? = null
    @SerializedName("hostel_room_id")
    @Expose
    val hostelRoomId: String? = null
    @SerializedName("fees_discount")
    @Expose
    val feesDiscount: String? = null

}