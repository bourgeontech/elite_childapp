package com.burgeon.parentapp.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Datamodels.ExamResult
import com.burgeon.parentapp.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Ajay K K on 2020-02-13.
 */
class ExamresultdetailsAdapter(
    val items: List<ExamResult>?,
    val context: FragmentActivity?,
    val manager: FragmentManager
) : RecyclerView.Adapter<ExamresultdetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamresultdetailsViewHolder {
        val view: ExamresultdetailsViewHolder =
            ExamresultdetailsViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.row_exam_details_result,
                    parent,
                    false
                )
            )
        return view;
    }

    override fun getItemCount(): Int {
        return items?.size!!
    }

    override fun onBindViewHolder(holder: ExamresultdetailsViewHolder, position: Int) {
        try {
            holder.tvSubject?.text = items?.get(position)?.subject
            holder.tvMarks?.text = "Marks:" + " " + items?.get(position)?.getMarks
            holder.tvDate?.text = parseDateToddMMyyyyExam(items?.get(position)?.date_of_exam)
        }catch (e:Exception)
        {
            print(e.printStackTrace().toString()+"<--------Error")
        }

        if (items?.get(position)?.attendence.equals("pre")) {
            if (items?.get(position)?.result.equals("Fail")) {
                holder.resultimage.text = "Failed"
                holder.resultimage.setTextColor(Color.parseColor("#FF0000"));
            } else {
                holder.resultimage.text = "Passed"
                holder.resultimage.setTextColor(Color.parseColor("#1c9e1f"));
            }
        } else {
            holder.resultimage.text = "Not attended"
            holder.resultimage.setTextColor(Color.parseColor("#3b1c9e"));
        }

    }
}

fun parseDateToddMMyyyyExam(time: String?): String? {
    val inputPattern = "yyyy-MM-dd"
    val outputPattern = "dd/MM/yyyy"
    val inputFormat = SimpleDateFormat(inputPattern)
    val outputFormat = SimpleDateFormat(outputPattern)
    var date: Date? = null
    var str: String? = null
    try {
        date = inputFormat.parse(time)
        str = outputFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return str
}

class ExamresultdetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvSubject = itemView.findViewById(R.id.tvSubject) as TextView
    val tvMarks = itemView.findViewById(R.id.tvMarks) as TextView
    val resultimage = itemView.findViewById(R.id.result_image) as TextView
    val tvDate = itemView.findViewById(R.id.tvDate) as TextView
}
