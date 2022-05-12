package com.burgeon.parentapp.Attendance


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.burgeon.parentapp.Datamodels.Attendancedetails

import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_attendance.*
import java.util.*
import com.applandeo.materialcalendarview.EventDay


class AttendanceFragment : Fragment(), AttendanceView {

    private var yearcurrent: Int = 0
    private var monthcurrent: Int = 0
    private var childId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    private val presenter = AttendancePresenter(this, AttendanceInteractor())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_attendance, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceObj = PreferenceRequestHelper(activity)
        childId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")

        yearcurrent = Calendar.getInstance().get(Calendar.YEAR)
        monthcurrent = Calendar.getInstance().get(Calendar.MONTH) + 1

        presenter.getHomedata(childId, yearcurrent.toString(), monthcurrent.toString())

        val calendar = Calendar.getInstance()
        val monthval = calendar.get(Calendar.MONTH)

       // calendar.add(Calendar.MONTH, monthval - 1)
        calendarView.setDate(calendar);

        calendarView.setOnPreviousPageChangeListener {
            try {

                if (monthcurrent > 0) {
                    monthcurrent--;
                } else {
                    monthcurrent = 12;
                    yearcurrent--;
                }

                presenter.getHomedata(childId, yearcurrent.toString(), monthcurrent.toString())
            }catch (e:Exception){
                print(e.printStackTrace().toString()+"<---1---Error")
            }
        }


        calendarView.setOnForwardPageChangeListener {
            try {

                if (monthcurrent < 12) {
                    monthcurrent++;
                } else {
                    monthcurrent = 0;
                    yearcurrent++
                }

                presenter.getHomedata(childId, yearcurrent.toString(), monthcurrent.toString())
            }catch (e:Exception)
            {
                print(e.printStackTrace().toString()+"<---2---Error")
            }

        }

        ivBack.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        tvTitle.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }


    }

    override fun showProgress() {
        pgProgress?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pgProgress?.visibility = View.GONE
    }

    override fun apiError() {
        //Toast.makeText(activity, "Please try later", Toast.LENGTH_LONG).show()
    }

    override fun setAttendanceData(data: List<Attendancedetails>?) {
        try {
            val events: MutableList<EventDay>? = ArrayList<EventDay>()
            val highlited: MutableList<Calendar>? = ArrayList<Calendar>()


            for (i in 0 until data?.size!!) {
                if (data?.get(i)?.type.equals("Present")) {

                    val calendar = Calendar.getInstance()
                    val date: String = data?.get(i)?.date!!;
                    val parts = date.split("-")
                    val year: Int = parts[0].toInt();
                    val month: Int = parts[1].toInt();
                    val day: Int = parts[2].toInt();

                    calendar.set(year, month - 1, day);
                    // highlited?.add(calendar)
                    events?.add(EventDay(calendar, R.drawable.ic_tick))
                } else if (data?.get(i)?.type.equals("Absent")) {

                    val calendar = Calendar.getInstance()
                    val date: String = data?.get(i)?.date!!;
                    val parts = date.split("-")
                    val year: Int = parts[0].toInt();
                    val month: Int = parts[1].toInt();
                    val day: Int = parts[2].toInt();

                    calendar.set(year, month - 1, day);

                    events?.add(EventDay(calendar, R.drawable.ic_close))
                }
            }


            calendarView?.setEvents(events)
        }catch (e: Exception)
        {
           print(e.printStackTrace().toString()+"<---3---Error")
        }

    }


}
