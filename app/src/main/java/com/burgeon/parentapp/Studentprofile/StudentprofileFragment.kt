package com.burgeon.parentapp.Studentprofile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.burgeon.parentapp.Datamodels.StudentprofileDetails

import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_studentprofile.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class StudentprofileFragment : Fragment(), StudentprofileView {

    private var childId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    private val presenter: StudentprofilePresenter = StudentprofilePresenter(
        this,
        StudentprofileInteractor()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_studentprofile, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceObj = PreferenceRequestHelper(activity)
        childId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")


        ivBack.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        tvTitle.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        presenter.getStudentprofile(childId!!)
    }

    override fun showProgress() {
        pgProgress?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pgProgress?.visibility = View.GONE
    }

    override fun setProfileData(child: StudentprofileDetails?) {

        tvName?.text = child?.firstname

        if (child?.gender.equals("F"))
            tvGender?.text = "Female"
        else if (child?.gender.equals("M"))
            tvGender?.text = "Male"


        tvClass?.text = child?._class + " " + child?.section
        tvDob?.text = "DOB:" + (parseDateToddMMyyyy(child?.dob))

        admission_no?.text = "NO:" + " " + child?.admissionNo
        admission_date?.text = "DATE" + " " + (parseDateToddMMyyyy(child?.admissionDate))
        current_address?.text = child?.currentAddress
        permanent_address?.text = child?.permanentAddress
        father_name?.text = child?.fatherName
        father_phone?.text = child?.fatherPhone
        father_occupation?.text = child?.fatherOccupation
        mother_name?.text = child?.motherName
        mother_phone?.text = child?.motherPhone
        mother_occupation?.text = child?.motherOccupation
        guardian_name?.text = child?.guardianName
        guardian_phone?.text = child?.guardianPhone
        guardian_occupation?.text = child?.guardianOccupation
    }

    override fun apiError() {
    }


    companion object {
    }
    fun parseDateToddMMyyyy(time: String?): String? {
        val inputPattern = "yyyy-MM-dd"
        val outputPattern = "dd/MM/yyyy"
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)
        var date: Date? = null
        var str: String? = null
        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str
    }
}
