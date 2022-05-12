package com.burgeon.parentapp.Dilaog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Adapter.ChapterdialogAdapter
import com.burgeon.parentapp.Datamodels.Chapter
import com.burgeon.parentapp.R
import kotlinx.android.synthetic.main.fragment_list_dialog.*

/**
 * Created by Ajay K K on 24/06/20.
 */
class ChapterDialog(val body: List<Chapter>) : DialogFragment() {

    interface ChapterDialogListener {
        fun onChapterDialogclicked(id: String, title: String)
    }

    private lateinit var OnChapterdetailsObj: ChapterdialogAdapter.OnChapterdetails
    var ChapterDataObj: ChapterDialogListener? = null


    fun setDialoglistener(ChapterDataobj: ChapterDialogListener?) {
        ChapterDataObj = ChapterDataobj
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

        tvTitle.setText("Chapters")

        OnChapterdetailsObj = object : ChapterdialogAdapter.OnChapterdetails {
            override fun onChapter(id: String, name: String) {
                ChapterDataObj?.onChapterDialogclicked(id, name)
                dismiss()
            }
        }

        rvList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        val adapter = fragmentManager?.let { ChapterdialogAdapter(body, activity, it) }
        adapter?.setChapterInterface(OnChapterdetailsObj)
        rvList.adapter = adapter
    }


}