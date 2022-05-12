package com.app.khmhsschild.SubjectList

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

import com.google.android.youtube.player.YouTubeStandalonePlayer
import droidninja.filepicker.FilePickerBuilder
import droidninja.filepicker.FilePickerConst
import kotlinx.android.synthetic.main.fragment_enquiry.*
import java.io.File
import java.lang.Exception
import java.util.ArrayList
import java.util.regex.Pattern


class EnquiryFragment : Fragment(), EnquiryView {

    private var studentId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    private var selectedDoc: Uri? = null
    private var class_id: String = ""
    private var selectedChapterId: String = ""
    private var selectedSubjectId: String = ""

    private val presenter = EnquiryPresenter(this, EnquiryInteractor())
    private var docPaths: ArrayList<String> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_enquiry, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceObj = PreferenceRequestHelper(activity)
        studentId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")


        ivBack.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        tvTitle.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }


        tvType.setOnClickListener {
            showAlertDialog()
        }


        btnSubmit.setOnClickListener {
            if(tvType.text.equals("Selet Type"))
                Toast.makeText(activity,"Select Type",Toast.LENGTH_LONG).show()
            else if(etMatter.text.length==0)
                Toast.makeText(activity,"Enter Matter",Toast.LENGTH_LONG).show()
            else
                presenter.submitData(studentId!!,tvType.text.toString(),etMatter.text.toString())
        }

    }

    private fun showAlertDialog() {
        try {
            val mDialogView =
                LayoutInflater.from(activity).inflate(R.layout.type_dialog, null)
            val mBuilder = AlertDialog.Builder(activity!!)
                .setView(mDialogView);

            // val  mAlertDialog = mBuilder.show()
            var tvSuggetion: TextView =
                mDialogView?.findViewById(R.id.tvSuggetion) as TextView

            var tvComplaints: TextView =
                mDialogView?.findViewById(R.id.tvComplaints) as TextView

            var tvRequirement: TextView =
                mDialogView?.findViewById(R.id.tvRequirement) as TextView

            val alertDialog: AlertDialog = mBuilder.create()
            alertDialog?.setCancelable(true)
            alertDialog?.show()
            tvSuggetion.setOnClickListener {
                tvType?.setText("Suggestion")
                alertDialog?.dismiss()
            }

            tvComplaints.setOnClickListener {
                tvType?.setText("Complaints")
                alertDialog?.dismiss()
            }

            tvRequirement.setOnClickListener {
                tvType?.setText("New Requirement")
                alertDialog?.dismiss()
            }


        } catch (e: Exception) {
        }
    }

    override fun showProgress() {
        pgProgress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pgProgress.visibility = View.GONE
    }

    override fun fileSubmitSuccess() {
        try {
            Toast.makeText(activity, "Successfully added", Toast.LENGTH_LONG).show()

            fragmentManager?.popBackStackImmediate()

        } catch (e: java.lang.Exception) {

        }
    }

    override fun fileSubmitError(s: String) {
        try {
            Toast.makeText(activity, s, Toast.LENGTH_LONG).show()
        } catch (e: java.lang.Exception) {

        }
    }

}
