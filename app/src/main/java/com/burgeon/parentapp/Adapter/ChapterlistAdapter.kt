package com.burgeon.parentapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Datamodels.Chapter
import com.burgeon.parentapp.R


/**
 * Created by Ajay K K on 2020-02-13.
 */
class ChapterdialogAdapter(
    var data: List<Chapter>?,
    var activity: FragmentActivity?,
    var fragmentManager: FragmentManager
) : RecyclerView.Adapter<GetWeekViewHolder>() {


    private lateinit var OnChapterdetailsval: OnChapterdetails

    public interface OnChapterdetails {
        fun onChapter(id: String, name: String)
    }

    fun setChapterInterface(Chapterdetails: OnChapterdetails) {
        OnChapterdetailsval = Chapterdetails
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetWeekViewHolder {
        val view: GetWeekViewHolder =
            GetWeekViewHolder(
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

    override fun onBindViewHolder(holder: GetWeekViewHolder, position: Int) {
        holder.tvWeek.text = data?.get(position)?.unitName

        holder.llContainer.setOnClickListener {
            data?.get(position)?.unitName?.let { it1 ->
                data?.get(position)?.id?.let { it2 ->
                    OnChapterdetailsval.onChapter(
                        it2,
                        it1
                    )
                }
            }
        }

    }
}

class GetWeekViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvWeek = itemView.findViewById(R.id.tvchaptersubject) as TextView
    val llContainer = itemView.findViewById(R.id.llContainersubch) as LinearLayout
}
