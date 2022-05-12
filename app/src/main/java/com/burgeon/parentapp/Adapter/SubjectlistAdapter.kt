package com.burgeon.parentapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Datamodels.SubjectData
import com.burgeon.parentapp.R


/**
 * Created by Ajay K K on 2020-02-13.
 */
class SubjectlistAdapter(
    var data: List<SubjectData>,
    var activity: FragmentActivity?,
    var fragmentManager: FragmentManager
) : RecyclerView.Adapter<SubjectlistAdapterViewHolder>() {


    private lateinit var OnSubjectdetailsval: OnSubjectdetails

    public interface OnSubjectdetails {
        fun onChapter(id: String, name: String)
    }

    fun setSubjectInterface(Chapterdetails: OnSubjectdetails) {
        OnSubjectdetailsval = Chapterdetails
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectlistAdapterViewHolder {
        val view: SubjectlistAdapterViewHolder =
            SubjectlistAdapterViewHolder(
                LayoutInflater.from(activity).inflate(
                    R.layout.row_chapter_subject,
                    parent,
                    false
                )
            )
        return view;
    }

    override fun getItemCount(): Int {
        return data?.size!!
    }

    override fun onBindViewHolder(holder: SubjectlistAdapterViewHolder, position: Int) {
        holder.tvWeek.text = data?.get(position)?.name

        holder.llContainer.setOnClickListener {
            data?.get(position)?.name?.let { it1 ->
                data?.get(position)?.id?.let { it2 ->
                    OnSubjectdetailsval.onChapter(
                        it2,
                        it1
                    )
                }
            }
        }

    }
}

class SubjectlistAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvWeek = itemView.findViewById(R.id.tvchaptersubject) as TextView
    val llContainer = itemView.findViewById(R.id.llContainersubch) as LinearLayout
}
