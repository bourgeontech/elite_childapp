package com.burgeon.parentapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Datamodels.ExamresultdetailsDatamodel
import com.burgeon.parentapp.R

/**
 * Created by Ajay K K on 2020-02-13.
 */
class ExamresultAdapter(
    val items: List<ExamresultdetailsDatamodel>?,
    val context: FragmentActivity?,
    val manager: FragmentManager
) : RecyclerView.Adapter<ExamresultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamresultViewHolder {
        val view: ExamresultViewHolder =
            ExamresultViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.row_exam_result,
                    parent,
                    false
                )
            )
        return view;
    }

    override fun getItemCount(): Int {
        return items?.size!!
    }

    override fun onBindViewHolder(holder: ExamresultViewHolder, position: Int) {
        holder.tvTitle.text = items?.get(position)?.examName

        if (items?.get(position)?.visiblity!!) {
            holder.rvResult.visibility = View.VISIBLE
        } else {
            holder.rvResult.visibility = View.GONE
        }

        holder.tvTitle.setOnClickListener {
            if(items?.get(position).visiblity ==true)
            {
                items?.get(position).visiblity = false
            }else {
                for (item in items) {
                    for (item in items.indices) {
                        items?.get(item).visiblity = false
                    }
                }
                items?.get(position).visiblity = true
            }
            notifyDataSetChanged()
        }


        holder.rvResult?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val examresultdetailsadapter =
            manager?.let { ExamresultdetailsAdapter(items?.get(position)?.examResult, context, it) }
        holder.rvResult?.adapter = examresultdetailsadapter
    }
}

class ExamresultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvTitle = itemView.findViewById(R.id.tvTitle) as TextView
    val rvResult = itemView.findViewById(R.id.rvExamresult) as RecyclerView

}
