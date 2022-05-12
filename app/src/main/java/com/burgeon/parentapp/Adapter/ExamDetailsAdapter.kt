package com.burgeon.parentapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Datamodels.ExamDetailsList
import com.burgeon.parentapp.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Ajay K K on 2020-02-13.
 */
class ExamDetailsAdapter(
    var id: List<ExamDetailsList>?,
    var activity: FragmentActivity?,
    var fragmentManager: FragmentManager
) : RecyclerView.Adapter<ExamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamViewHolder {
        val view: ExamViewHolder =
            ExamViewHolder(
                LayoutInflater.from(activity).inflate(
                    R.layout.row_exam_details,
                    parent,
                    false
                )
            )
        return view;
    }

    override fun getItemCount(): Int {
        return id?.size!!
    }

    override fun onBindViewHolder(holder: ExamViewHolder, position: Int) {
       holder.txtSubject.text = id?.get(position)?.subject

        var mydate = parseDateToddMMyyyy( id?.get(position)?.dateOfExam)
        holder.txtDate.text = mydate
        holder.txtDuration.text = id?.get(position)?.duration

//
//        val examresultdetailsadapter =
//            fragmentManager?.let { ExamresultdetailsAdapter(items?.get(position)?.examResult, context, it) }
//        holder.rvResult.adapter = examresultdetailsadapter
    }
}

class ExamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val txtSubject = itemView.findViewById(R.id.txtSubject) as TextView
    val txtDate = itemView.findViewById(R.id.txtDate) as TextView
    val txtDuration = itemView.findViewById(R.id.txtDuration) as TextView
  //  val rvResult = itemView.findViewById(R.id.rvExamresult) as RecyclerView
}

fun parseDateTodddMMyyyy(time: String?): String? {
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
