package com.burgeon.parentapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Datamodels.RemarkDetails
import com.burgeon.parentapp.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Ajay K K on 2020-02-13.
 */
class RemarkAdapter(
    var id: List<RemarkDetails>?,
    var activity: FragmentActivity?,
    var fragmentManager: FragmentManager,
    val ondelete: RemarkDelete
) : RecyclerView.Adapter<RemarkViewHolder>() {
    interface RemarkDelete {
        fun onclicked(id: String)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemarkViewHolder {
        val view: RemarkViewHolder =
            RemarkViewHolder(
                LayoutInflater.from(activity).inflate(
                    R.layout.row_remark,
                    parent,
                    false
                )
            )
        return view;
    }

    override fun getItemCount(): Int {
        return id?.size!!
    }

    override fun onBindViewHolder(holder: RemarkViewHolder, position: Int) {

        var mydate = parseDateToddMMyyyy(id?.get(position)?.date)
        val time: Array<String> = id?.get(position)?.date?.split("-")!!.toTypedArray()
        val year = time[0].trim { it <= ' ' }.toInt()
        val month = time[1].trim { it <= ' ' }.toInt()
        val day = time[2].trim { it <= ' ' }.toInt()
        val calendar: Calendar =
            GregorianCalendar(
                year,
                month,
                day
            ) // Note that Month value is 0-based. e.g., 0 for January.

        val reslut = calendar[Calendar.DAY_OF_WEEK]
        val result1 = reslut - 1
        when (result1) {
            Calendar.MONDAY -> holder.txtDay.text = "MON"
            Calendar.TUESDAY -> holder.txtDay.text = "TUE"
            Calendar.WEDNESDAY -> holder.txtDay.text = "WED"
            Calendar.THURSDAY -> holder.txtDay.text = "THU"
            Calendar.FRIDAY -> holder.txtDay.text = "FRI"
            Calendar.SATURDAY -> holder.txtDay.text = "SAT"
            Calendar.SUNDAY -> holder.txtDay.text = "SUN"

        }
        holder.txtTitle.text = "Remark : " + id?.get(position)?.remark
        holder.txtDate.text = mydate
        holder.txtDelete.setOnClickListener {
            ondelete.onclicked(id?.get(position)?.id!!)
        }

//        val examresultdetailsadapter =
//            fragmentManager?.let { ExamresultdetailsAdapter(items?.get(position)?.examResult, context, it) }
//        holder.rvResult.adapter = examresultdetailsadapter
    }
}

fun parseDateToddMMyyyy(time: String?): String? {
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

class RemarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val txtTitle = itemView.findViewById(R.id.txtTitle) as TextView
    val txtDate = itemView.findViewById(R.id.txtDate) as TextView
    val txtDay = itemView.findViewById(R.id.txtDay) as TextView
    val txtDelete= itemView.findViewById(R.id.txtDelete) as TextView
    //  val rvResult = itemView.findViewById(R.id.rvExamresult) as RecyclerView
}
