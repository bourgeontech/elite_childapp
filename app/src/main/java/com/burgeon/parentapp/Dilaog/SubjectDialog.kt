package com.burgeon.parentapp.Dilaog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Adapter.SubjectlistAdapter
import com.burgeon.parentapp.Datamodels.SubjectData
import com.burgeon.parentapp.R
import kotlinx.android.synthetic.main.fragment_list_dialog.*

/**
 * Created by Ajay K K on 24/06/20.
 */
class SubjectDialog(val body: List<SubjectData>) : DialogFragment() {

    interface DialogListener {
        fun onDialogclicked(id: String, title: String)
    }

    private lateinit var OnSubjectdetailsObj: SubjectlistAdapter.OnSubjectdetails
    var scannedDataObj: DialogListener? = null


    fun setDialoglistener(scannedDataobj: DialogListener?) {
        scannedDataObj = scannedDataobj
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        tvTitle.setText("Subject")

        OnSubjectdetailsObj = object : SubjectlistAdapter.OnSubjectdetails {
            override fun onChapter(id: String, name: String) {
                scannedDataObj?.onDialogclicked(id, name)
                dismiss()
            }
        }

        rvList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        val adapter = fragmentManager?.let { SubjectlistAdapter(body, activity, it) }
        adapter?.setSubjectInterface(OnSubjectdetailsObj)
        rvList.adapter = adapter
    }


}