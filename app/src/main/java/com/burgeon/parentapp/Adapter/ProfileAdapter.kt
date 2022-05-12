package com.burgeon.parentapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Dashboard.DasboardFragment
import com.burgeon.parentapp.Datamodels.childdetailsDatamodel
import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import com.bumptech.glide.Glide

/**
 * Created by Ajay K K on 2020-02-14.
 */
class ProfileAdapter(
    val items: List<childdetailsDatamodel>?,
    val context: FragmentActivity?,
    val manager: FragmentManager?,
    val preferenceObj: PreferenceRequestHelper,
    val positionval: Int?,
    val childId: String?
) : RecyclerView.Adapter<ProfileViewHolder>() {
    private var userId: String=""
    private lateinit var preferenceObj2: PreferenceRequestHelper
    private var row_index: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {

        val view: ProfileViewHolder =
            ProfileViewHolder(
                LayoutInflater.from(context!!).inflate(
                    R.layout.row_profile_two,
                    parent,
                    false
                )
            )
        return view;

    }

    override fun getItemCount(): Int {
       // return items?.size!!
        return items?.size!!
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {

        preferenceObj2 = PreferenceRequestHelper(context)
        userId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")
        holder.tvName.text = items?.get(position)?.firstname
        holder.tvClass.text = items?.get(position)?._class + " " + items?.get(position)?.section
        println(userId+"............selectid")
        println(items?.get(position)?.id+"............selectid")
        if(userId==items?.get(position)?.id)
        {
            Glide.with(context!!).load(R.drawable.tickgreen).into(holder.ivTick)
        }else {
            Glide.with(context!!).load(R.drawable.tickgrey).into(holder.ivTick)
        }
//        holder.tvTeachername.text = items?.get(position)?.teachername
//        holder.tvTeacherphone.text = items?.get(position)?.teacherphone
//        holder.tvTeacheremail.text = items?.get(position)?.teacheremail

        if (childId?.equals(items?.get(position)?.id)!!) {
            row_index = position;
          //  holder.tvSelect.text = "Selected"
        }

        holder.llContainer.setOnClickListener {
//Toast.makeText(context,"toast",Toast.LENGTH_SHORT).show()
            preferenceObj.setValue(Constants.PRES_STUDENT_NAME, items?.get(position)?.firstname);
            preferenceObj.setValue(Constants.PRES_STUDENT_ID, items?.get(position)?.id);
            preferenceObj.setValue(Constants.PRES_STUDENT_CLASS, items?.get(position)?._class);
            preferenceObj.setValue(Constants.PRES_STUDENT_CLASS_ID, items?.get(position)?.classId);
            preferenceObj.setValue(Constants.PRES_STUDENT_SECTION, items?.get(position)?.section);
            preferenceObj.setValue(Constants.PRES_TEACHER_ID, items?.get(position)?.teacher_id);
            preferenceObj.setValue(
                Constants.PRES_STUDENT_SECTION_ID,
                items?.get(position)?.sectionId
            );
            preferenceObj.setValue(
                Constants.PRES_STUDENT_ADMISSION_NO,
                items?.get(position)?.admissionNo
            );
            preferenceObj.setValue(Constants.PRES_STUDENT_ROLL_NO, items?.get(position)?.rollNo);

            //if (positionval == 0) {
                manager?.beginTransaction()?.replace(
                    R.id.fl_container,
                    DasboardFragment()
                )?.addToBackStack("")?.commit()

//            } else {
//                manager?.popBackStackImmediate()
//            }


        }
/*
      /*  holder.llContainer.setOnClickListener {
            row_index = position;
            notifyDataSetChanged();
        }
*/

 */
//        if (row_index == position) {
//           // holder.llContainer.setBackgroundResource(R.drawable.selected_background)
//            Glide.with(context).load(R.drawable.tickgreen).into(holder.ivTick)
//        } else {
//           // holder.llContainer.setBackgroundResource(R.drawable.deselected_background)
//            Glide.with(context).load(R.drawable.tickgrey).into(holder.ivTick)
//        }

    }
}

class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvName = itemView.findViewById(R.id.tvName) as TextView
    val tvClass = itemView.findViewById(R.id.tvClass) as TextView
//    val tvSelect = itemView.findViewById(R.id.tvSelect) as TextView
//    val tvTeachername = itemView.findViewById(R.id.tvTeachername) as TextView
//    val tvTeacherphone = itemView.findViewById(R.id.tvTeacherphone) as TextView
//    val ivCall = itemView.findViewById(R.id.ivCall) as ImageView
   val llContainer = itemView.findViewById(R.id.llContainer) as LinearLayout
//    val tvTeacheremail = itemView.findViewById(R.id.tvTeacheremail) as TextView
    val ivTick=itemView.findViewById(R.id.ivTick) as ImageView



}
