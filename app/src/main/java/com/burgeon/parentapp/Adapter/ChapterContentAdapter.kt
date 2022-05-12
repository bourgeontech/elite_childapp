package com.burgeon.parentapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Datamodels.contentDetails
import com.burgeon.parentapp.R


/**
 * Created by Ajay K K on 2020-02-13.
 */
class ChapterContentAdapter(
    var data: List<contentDetails>?,
    var activity: FragmentActivity?,
    var fragmentManager: FragmentManager
) : RecyclerView.Adapter<GetContentViewHolder>() {


    private lateinit var OnChaptercontenetdetailsval: OnChaptercontenetdetails

    public interface OnChaptercontenetdetails {
        fun onChaptercontenet(
            video: String,
            audio: String,
            file: String,
            name: String,
            contentId:String
        )
    }

    fun setChapterInterface(Chaptercontenetdetails: OnChaptercontenetdetails) {
        OnChaptercontenetdetailsval = Chaptercontenetdetails
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetContentViewHolder {
        val view: GetContentViewHolder =
            GetContentViewHolder(
                LayoutInflater.from(activity).inflate(
                    R.layout.row_chapter_content,
                    parent,
                    false
                )
            )
        return view;
    }

    override fun getItemCount(): Int {
        return data?.size!!
    }

    override fun onBindViewHolder(holder: GetContentViewHolder, position: Int) {
        holder.tvWeek.text = data?.get(position)?.name

        holder.llContainer.setOnClickListener {
          print(data?.get(position)?.document!!+"...............docu")
            OnChaptercontenetdetailsval.onChaptercontenet(data?.get(position)?.videoUrl!!,
                data?.get(position)?.audio!!,data?.get(position)?.document!!,data?.get(position)?.name!!,
                data?.get(position)?.id!!)
        }

    }
}

class GetContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvWeek = itemView.findViewById(R.id.tvchaptersubject) as TextView
    val llContainer = itemView.findViewById(R.id.llContainersubch) as LinearLayout
}
