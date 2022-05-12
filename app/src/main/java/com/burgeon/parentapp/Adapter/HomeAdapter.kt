package com.burgeon.parentapp.Adapter

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.app.khmhsschild.SubjectList.EnquiryFragment
import com.burgeon.parentapp.Attendance.AttendanceFragment
import com.burgeon.parentapp.Changepassword.ChangepasswordFragment
import com.burgeon.parentapp.Circular.CircularFragment
import com.burgeon.parentapp.Examresult.ExamresultFragment
import com.burgeon.parentapp.GetExamination.ExaminationFragment
import com.burgeon.parentapp.Homework.ExamdocumentFragment
import com.burgeon.parentapp.Homework.HomeworkdataFragment
import com.burgeon.parentapp.R
import com.burgeon.parentapp.Remark.RemarkFragment
import com.burgeon.parentapp.Studentprofile.StudentprofileFragment
import com.burgeon.parentapp.SubjectList.LessonListFragment
import com.burgeon.parentapp.TimeTable.TimetableFragment
import com.bumptech.glide.Glide
import com.burgeon.parentapp.Communication.CommunicationFragment
import com.burgeon.parentapp.OnlineClass.OnlineClassFragment
import com.burgeon.parentapp.Profile.ProfileView

class HomeAdapter(
    val items: ArrayList<String>,
    val context: FragmentActivity?,
    val fragmentManager: FragmentManager?
) :
    RecyclerView.Adapter<HomeViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewholder {
        val view: HomeViewholder =
            HomeViewholder(LayoutInflater.from(context).inflate(R.layout.row_home, parent, false))
        return view;
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HomeViewholder, position: Int) {

        holder.tvTitle.setText(items.get(position))
//        if (position == 9) {
//            holder.txtcomming.visibility = View.VISIBLE
//        } else {
//            holder.txtcomming.visibility = View.GONE
//        }
        var greenProgressbar =
            ContextCompat.getDrawable(context!!, R.drawable.profile)

//        if (position == 0) {
//            greenProgressbar =
//                ContextCompat.getDrawable(context!!, R.drawable.ic_first)
//        } else if (position == 1) {
//            greenProgressbar =
//                ContextCompat.getDrawable(context!!, R.drawable.ic_second)
//        } else if (position == 2) {
//            greenProgressbar =
//                ContextCompat.getDrawable(context!!, R.drawable.ic_attendance)
//        } else if (position == 3) {
//            greenProgressbar =
//                ContextCompat.getDrawable(context!!, R.drawable.ic_timetable)
//        } else if (position == 4) {
//            greenProgressbar =
//                ContextCompat.getDrawable(context!!, R.drawable.ic_exams)
//        } else if (position == 5) {
//            greenProgressbar =
//                ContextCompat.getDrawable(context!!, R.drawable.ic_communication)
//        } else if (position == 6) {
//            greenProgressbar =
//                ContextCompat.getDrawable(context!!, R.drawable.ic_examresult)
//        } else if (position == 7) {
//            greenProgressbar =
//                ContextCompat.getDrawable(context!!, R.drawable.ic_homework)
//        } else if (position == 8) {
//            greenProgressbar =
//                ContextCompat.getDrawable(context!!, R.drawable.ic_passwd)
//        }

        if (position == 0) {
            greenProgressbar =
                ContextCompat.getDrawable(context!!, R.drawable.profile)
        } else if (position == 1) {
            greenProgressbar =
                ContextCompat.getDrawable(context!!, R.drawable.homeworknew)
        } else if (position == 2) {
            greenProgressbar =
                ContextCompat.getDrawable(context!!, R.drawable.exam)
        } else if (position == 3) {
            greenProgressbar =
                ContextCompat.getDrawable(context!!, R.drawable.circular)
        } else if (position == 4) {
            greenProgressbar =
                ContextCompat.getDrawable(context!!, R.drawable.exam_date)
        } else if (position == 5) {
            greenProgressbar =
                ContextCompat.getDrawable(context!!, R.drawable.exam_result)
        } else if (position == 6) {
            greenProgressbar =
                ContextCompat.getDrawable(context!!, R.drawable.fees)
        }
        else if (position == 7) {
            greenProgressbar =
                ContextCompat.getDrawable(context!!, R.drawable.time_table)
        } else if (position == 8) {
            greenProgressbar =
                ContextCompat.getDrawable(context!!, R.drawable.remark)
        } else if (position == 9) {
            greenProgressbar =
                ContextCompat.getDrawable(context!!, R.drawable.attendence)
        }
//        else if (position == 10) {
//            greenProgressbar =
//                ContextCompat.getDrawable(context!!, R.drawable.new_gallary)
//        }
//        else if (position == 10) {
//            greenProgressbar =
//                ContextCompat.getDrawable(context!!, R.drawable.ic_conversation)
//        }
        else if (position == 10) {
            greenProgressbar =
                ContextCompat.getDrawable(context!!, R.drawable.ticket)
        }
//        else if (position == 11) {
//            greenProgressbar =
//                ContextCompat.getDrawable(context!!, R.drawable.live_class)
//        }
        else if (position == 11) {
            greenProgressbar =
                ContextCompat.getDrawable(context!!, R.drawable.change_password)
        }


        Glide.with(context!!).load(greenProgressbar).into(holder.ivImage)

        holder.ivImage.setOnClickListener {

            if (position == 0)
                fragmentManager?.beginTransaction()?.replace(
                    R.id.fl_container,
                    StudentprofileFragment()
                )?.addToBackStack("")?.commit()
            else if (position == 1) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context!!.checkSelfPermission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) !== PackageManager.PERMISSION_GRANTED
                ) {
                    context.requestPermissions(
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        1001
                    )
                } else {

                    fragmentManager?.beginTransaction()?.replace(
                        R.id.fl_container,
                        HomeworkdataFragment()
                    )?.addToBackStack("")?.commit()
                }
            } else if (position == 2) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context!!.checkSelfPermission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) !== PackageManager.PERMISSION_GRANTED
                ) {
                    context.requestPermissions(
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        1001
                    )
                } else {

                    fragmentManager?.beginTransaction()?.replace(
                        R.id.fl_container,
                        ExamdocumentFragment()
                    )?.addToBackStack("")?.commit()
                }
            } else if (position == 3) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context!!.checkSelfPermission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) !== PackageManager.PERMISSION_GRANTED
                ) {
                    context.requestPermissions(
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        1001
                    )
                } else {

                    fragmentManager?.beginTransaction()?.replace(
                        R.id.fl_container,
                        CircularFragment()
                    )?.addToBackStack("")?.commit()
                }
            } else if (position == 4)
                fragmentManager?.beginTransaction()?.replace(
                    R.id.fl_container,
                    ExaminationFragment()
                )?.addToBackStack("")?.commit()
            else if (position == 5)
                fragmentManager?.beginTransaction()?.replace(
                    R.id.fl_container,
                    ExamresultFragment()
                )?.addToBackStack("")?.commit()
//            else if (position == 6) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context!!.checkSelfPermission(
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE
//                    ) !== PackageManager.PERMISSION_GRANTED
//                ) {
//                    context.requestPermissions(
//                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
//                        1001
//                    )
//                } else {
//                    fragmentManager?.beginTransaction()?.replace(
//                        R.id.fl_container,
//                        LessonListFragment()
//                    )?.addToBackStack("")?.commit()
//                }
//            }
        else if (position == 7)
                fragmentManager?.beginTransaction()?.replace(
                    R.id.fl_container,
                    TimetableFragment()
                )?.addToBackStack("")?.commit()
            else if (position == 8)
                fragmentManager?.beginTransaction()?.replace(
                    R.id.fl_container,
                    RemarkFragment()
                )?.addToBackStack("")?.commit()
            else if (position == 9)
                fragmentManager?.beginTransaction()?.replace(
                    R.id.fl_container,
                    AttendanceFragment()
                )?.addToBackStack("")?.commit()
//            else if (position == 10)
//                fragmentManager?.beginTransaction()?.replace(
//                    R.id.fl_container,
//                    EnquiryFragment()
//                )?.addToBackStack("")?.commit()
            else if (position == 10)
                fragmentManager?.beginTransaction()?.replace(
                    R.id.fl_container,
                    CommunicationFragment()
                )?.addToBackStack("")?.commit()
//            else if (position == 10)
//                fragmentManager?.beginTransaction()?.replace(
//                    R.id.fl_container,
//                    OnlineClassFragment()
//                )?.addToBackStack("")?.commit()
            else if (position == 11)
                fragmentManager?.beginTransaction()?.replace(
                    R.id.fl_container,
                    ChangepasswordFragment()
                )?.addToBackStack("")?.commit()
        }
    }

    fun getValues(): ArrayList<String> {
        return items
    }
}

class HomeViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivImage = itemView.findViewById(R.id.ivImage) as ImageView
    val tvTitle = itemView.findViewById(R.id.tvTitle) as TextView
    val txtcomming = itemView.findViewById(R.id.txtcomming) as TextView

}
