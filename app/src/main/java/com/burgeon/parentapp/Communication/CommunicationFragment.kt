package com.burgeon.parentapp.Communication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.app.khmhsschild.SubjectList.CommunicationInteractor
import com.app.khmhsschild.SubjectList.CommunicationPresenter
import com.app.khmhsschild.SubjectList.CommunicationView
import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_communication.*
import java.lang.Exception


class CommunicationFragment : Fragment(),CommunicationView {
    private var teacherId: String=""
    private var studentId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    private val presenter=CommunicationPresenter(this, CommunicationInteractor())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View= inflater.inflate(R.layout.fragment_communication, container, false)
         return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceObj = PreferenceRequestHelper(activity)
        studentId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")
        teacherId = preferenceObj.getStringValue(Constants.PRES_TEACHER_ID, "")


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
                Toast.makeText(activity,"Select Type", Toast.LENGTH_LONG).show()
            else if(etMatter.text.length==0)
                Toast.makeText(activity,"Enter", Toast.LENGTH_LONG).show()
            else
                presenter.submitData(  teacherId,studentId!!,tvType.text.toString(),etMatter.text.toString())
        }
    }

    private fun showAlertDialog() {
        try {
            val mDialogView =
                LayoutInflater.from(activity).inflate(R.layout.type_dialog_two, null)
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
                tvType?.setText("Complaint ")
                alertDialog?.dismiss()
            }

            tvComplaints.setOnClickListener {
                tvType?.setText("Feedback")
                alertDialog?.dismiss()
            }

            tvRequirement.setOnClickListener {
                tvType?.setText("General")
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