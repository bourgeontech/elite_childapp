package com.burgeon.parentapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Datamodels.GetExaminationList
import com.burgeon.parentapp.ExamDetails.ExamDetailsFragment
import com.burgeon.parentapp.R

/**
 * Created by Ajay K K on 2020-02-13.
 */
class GetExamAdapter(
   var id: List<GetExaminationList>?,
   var activity: FragmentActivity?,
   var fragmentManager: FragmentManager
) : RecyclerView.Adapter<GetExamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetExamViewHolder {
        val view: GetExamViewHolder =
            GetExamViewHolder(
                LayoutInflater.from(activity).inflate(
                    R.layout.row_exam,
                    parent,
                    false
                )
            )
        return view;
    }

    override fun getItemCount(): Int {
        return id?.size!!
    }

    override fun onBindViewHolder(holder: GetExamViewHolder, position: Int) {
       holder.tvTitle.text = id?.get(position)?.name
        holder.tvTitle.setOnClickListener {
            var examdetailsfragment =
                ExamDetailsFragment.newInstance(
                    id?.get(position)?.id!!
                )

            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fl_container,
                    examdetailsfragment,
                    ExamDetailsFragment.javaClass.name
                )
                ?.addToBackStack("")?.commit()
          //  fragmentManager.beginTransaction().replace(R.id.fl_container,ExamDetailsFragment()).addToBackStack("").commitAllowingStateLoss()
        }
//
//        val examresultdetailsadapter =
//            fragmentManager?.let { ExamresultdetailsAdapter(items?.get(position)?.examResult, context, it) }
//        holder.rvResult.adapter = examresultdetailsadapter
    }
}

class GetExamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvTitle = itemView.findViewById(R.id.tvTitle) as TextView
  //  val rvResult = itemView.findViewById(R.id.rvExamresult) as RecyclerView
}
