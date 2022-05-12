package com.burgeon.parentapp.Uploadeddoc

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Adapter.ChapterContentAdapter
import com.burgeon.parentapp.Adapter.UploadeddocDetailsAdapter
import com.burgeon.parentapp.Datamodels.Uploadeddetails
import com.burgeon.parentapp.Dilaog.ChapterDialog
import com.burgeon.parentapp.Dilaog.SubjectDialog
import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_docview.*


class UploadeddocFragment : Fragment(), UploadeddocView {

    private lateinit var onDownloadDocObj: UploadeddocDetailsAdapter.onDownloadDoc
    private var studentId: String? = ""
    private var removedPosition: Int = 0
    private lateinit var onRemoveDocObj: UploadeddocDetailsAdapter.onRemoveDoc
    private var type: String? = ""
    private var homework_id: String? = ""
    private lateinit var selectedChapterContent: List<Uploadeddetails>
    private lateinit var chapterContentObj: ChapterContentAdapter.OnChaptercontenetdetails
    private lateinit var selectedChapterId: String
    private lateinit var ChapterDataobj: ChapterDialog.ChapterDialogListener
    private lateinit var selectedSubjectId: String
    private lateinit var subjectDataObj: SubjectDialog.DialogListener

    private val presenter = UploadeddocPresenter(this, UploadeddocInteractor())


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
        val view: View = inflater.inflate(R.layout.fragment_docview, container, false)
        return view
    }


    override fun onResume() {
        super.onResume()

        /* if (this::selectedChapterContent.isInitialized && selectedChapterContent != null) {
             rvLesson.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
             val adapter =
                 fragmentManager?.let {
                     UploadeddocDetailsAdapter(
                         selectedChapterContent,
                         activity,
                         it
                     )
                 }
             adapter?.setRemovedoc(onRemoveDocObj)
             rvLesson.adapter = adapter
         }*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var preferenceObj = PreferenceRequestHelper(activity)
        var classId = preferenceObj.getStringValue(Constants.PRES_STUDENT_CLASS_ID, "")
        studentId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")


        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        onRemoveDocObj = object : UploadeddocDetailsAdapter.onRemoveDoc {
            override fun onRemove(id: String, position: Int) {
                removedPosition = position;
                if (activity != null) {
                    val builder = AlertDialog.Builder(activity!!)
                    builder.setTitle("Delete")
                    builder.setMessage("Do you want to Delete")
                    builder.setIcon(android.R.drawable.ic_dialog_alert)

                    builder.setPositiveButton("Yes") { dialogInterface, which ->
                        presenter.deleteDoc(studentId.toString(), homework_id.toString(), id)
                        dialogInterface.dismiss()
                    }

                    builder.setNegativeButton("No") { dialogInterface, which ->
                        dialogInterface.dismiss()
                    }
                    val alertDialog: AlertDialog = builder.create()

                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }
            }
        }

        onDownloadDocObj = object : UploadeddocDetailsAdapter.onDownloadDoc {
            override fun onDownload(path: String) {
                presenter.downloadfile(path)
            }
        }

        ivBack.setOnClickListener {
            try {
                fragmentManager?.popBackStackImmediate()
            } catch (e: Exception) {

            }
        }

        presenter.getDoc(studentId.toString(), homework_id.toString(), type.toString())

    }

    override fun showProgress() {
        pgProgressdoc?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pgProgressdoc?.visibility = View.GONE
    }


    override fun showChapterContent(data: List<Uploadeddetails>?) {

        selectedChapterContent = data!!

        rvLesson.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        val adapter =
            fragmentManager?.let { UploadeddocDetailsAdapter(selectedChapterContent, activity, it) }
        adapter?.setRemovedoc(onRemoveDocObj)
        adapter?.setDownloaddoc(onDownloadDocObj)
        rvLesson.adapter = adapter

    }

    override fun showChapterContentError(msg: String) {
        try {
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
        }
    }

    override fun deleteError(msg: Any?) {
        try {
            Toast.makeText(activity, msg.toString(), Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
        }
    }


    override fun delSuccess(msg: Any?) {
        try {
            //selectedChapterContent?.drop(removedPosition)

            rvLesson?.adapter = null
            presenter?.getDoc(studentId.toString(), homework_id.toString(), type.toString())

            Toast.makeText(activity, msg.toString(), Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
        }
    }

    override fun fildownsuccess(intent: Intent) {
        try {
            activity?.startActivity(intent)
            Toast.makeText(activity, "File Downloaded", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
        }
    }

    override fun filedownerror() {
        try {
            Toast.makeText(activity, "There is some problem", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
        }
    }


    override fun apiError() {
    }

    companion object {
        @JvmStatic
        fun newInstance(
            homework_id: String,
            type: String
        ) =
            UploadeddocFragment().apply {
                arguments = Bundle().apply {
                    putString("homework_id", homework_id)
                    putString("type", type)
                }
            }
    }
}
