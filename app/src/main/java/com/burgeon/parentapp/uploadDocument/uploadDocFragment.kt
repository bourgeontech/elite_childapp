package com.burgeon.parentapp.SubjectList

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.burgeon.parentapp.Datamodels.SubjectData
import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper

import droidninja.filepicker.FilePickerBuilder
import droidninja.filepicker.FilePickerConst
import kotlinx.android.synthetic.main.fragment_subject_list.pgProgresslesson
import kotlinx.android.synthetic.main.fragment_upload_doc.*
import java.io.File
import java.util.ArrayList


class uploadDocFragment : Fragment(), uploadDocView {

    private var type: String? = ""
    private var homework_id: String? = ""
    private var selectedDoc: Uri? = null
    private var class_id: String = ""
    private var selectedChapterId: String = ""
    private var selectedSubjectId: String = ""
    private lateinit var subjectData: List<SubjectData>

    private val presenter = uploadDocPresenter(this, uploadDocInteractor())
    private var docPaths: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            homework_id = it.getString("homework_id")
            type = it.getString("type")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_upload_doc, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var preferenceObj = PreferenceRequestHelper(activity)
        var userId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")

        if (type.equals("exam")) {
            cbCompleted.visibility = View.VISIBLE
        } else {
            cbCompleted.visibility = View.GONE
        }


        ivBack.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        tvTitle.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        llupload.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity!!.checkSelfPermission(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1001
                )
            } else {
                FilePickerBuilder.instance.setMaxCount(1)
                    .setSelectedFiles(docPaths)
                    .setActivityTheme(R.style.FilePickerTheme)
                    .pickFile(this)
            }
        }



        btnUploadcontent.setOnClickListener {

            if (type.equals("exam")) {
                //Toast.makeText(activity,"if working",Toast.LENGTH_SHORT).show()
                print("1..ddd.."+homework_id)
                presenter.submitData(
                    homework_id!!,
                    userId, etTitle.text.toString(),
                    etRemark.text.toString(), docPaths, cbCompleted.isChecked,type
                )
            } else {
              //  Toast.makeText(activity,"else working..."+homework_id,Toast.LENGTH_SHORT).show()
                print("2..ddd.."+homework_id)
                presenter.submitData(
                    homework_id!!,
                    userId, etTitle.text.toString(),
                    etRemark.text.toString(), docPaths, false,type
                )
            }
        }

    }

    override fun showProgress() {
        pgProgresslesson.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pgProgresslesson.visibility = View.GONE
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

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?

    ) {
        super.onActivityResult(requestCode, resultCode, data)
        val code = requestCode.toString()

        if (requestCode == 111 && resultCode == Activity.RESULT_OK) {
            selectedDoc = data?.data //The uri with the location of the file
            val beforecompress = File(selectedDoc?.path)
        }

        if (requestCode == FilePickerConst.REQUEST_CODE_DOC) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                docPaths = ArrayList<String>();
                docPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS)!!);
                tvDoc.setText(docPaths.get(0).toString())
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            homework_id: String,
            type: String
        ) =
            uploadDocFragment().apply {
                arguments = Bundle().apply {
                    putString("homework_id", homework_id)
                    putString("type", type)
                }
            }
    }

}
