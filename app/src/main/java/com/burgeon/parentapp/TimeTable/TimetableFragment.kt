package com.burgeon.parentapp.TimeTable


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.burgeon.parentapp.Adapter.TimetableAdapter
import com.burgeon.parentapp.Datamodels.TimetableDatamodel

import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_timetable.*
import androidx.recyclerview.widget.DividerItemDecoration
import com.burgeon.parentapp.R
import java.lang.Exception


class TimetableFragment : Fragment(), TimeTableView {

    private var childId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    private val presenter = TimetablePresenter(this, TimetableInteractor())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_timetable, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceObj = PreferenceRequestHelper(activity)
        childId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")

        llfirst.visibility = View.VISIBLE
        llsecond.visibility = View.GONE

        tvFirst.text = "Monday"
        tvSecond.text = "Tuesday"
        tvThird.text = "Wednesday"


        ivBefore.setOnClickListener {
            if (llsecond?.visibility == View.VISIBLE) {
                llfirst.visibility = View.VISIBLE
                llsecond.visibility = View.GONE

                tvFirst.text = "Monday"
                tvSecond.text = "Tuesday"
                tvThird.text = "Wednesday"

            } else {
                llsecond.visibility = View.VISIBLE
                llfirst.visibility = View.GONE

                tvFirst.text = "Thursday"
                tvSecond.text = "Friday"
                tvThird.text = "Saturday"
            }
        }

        ivNext.setOnClickListener {
            if (llsecond?.visibility == View.VISIBLE) {
                llfirst.visibility = View.VISIBLE
                llsecond.visibility = View.GONE

                tvFirst.text = "Monday"
                tvSecond.text = "Tuesday"
                tvThird.text = "Wednesday"


            } else {
                llsecond.visibility = View.VISIBLE
                llfirst.visibility = View.GONE

                tvFirst.text = "Thursday"
                tvSecond.text = "Friday"
                tvThird.text = "Saturday"
            }
        }

        ivBack.setOnClickListener {
            fragmentManager?.popBackStackImmediate();
        }

        tvTitle.setOnClickListener {
            fragmentManager?.popBackStackImmediate();
        }

        getTimetableData()

    }

    private fun getTimetableData() {
        presenter.getTimetable(childId!!)
    }

    override fun showProgress() {
        pgProgress?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pgProgress?.visibility = View.GONE
    }

    override fun setTimeTableData(child: TimetableDatamodel) {

        rvFirst?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.HORIZONTAL
            )
        )
        rvFirst?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        rvSecond?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.HORIZONTAL
            )
        )
        rvSecond?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        rvThird?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.HORIZONTAL
            )
        )
        rvThird?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        rvFourth?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.HORIZONTAL
            )
        )
        rvFourth?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        rvFifth?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.HORIZONTAL
            )
        )
        rvFifth?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        rvSixth?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.HORIZONTAL
            )
        )
        rvSixth?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )


        rvFirst?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val first = fragmentManager?.let { TimetableAdapter(child, activity, it, 0) }
        rvFirst?.adapter = first

        rvSecond?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val second = fragmentManager?.let { TimetableAdapter(child, activity, it, 1) }
        rvSecond?.adapter = second

        rvThird?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val third = fragmentManager?.let { TimetableAdapter(child, activity, it, 2) }
        rvThird?.adapter = third

        rvFourth?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val fourth = fragmentManager?.let { TimetableAdapter(child, activity, it, 3) }
        rvFourth?.adapter = fourth

        rvFifth?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val fifth = fragmentManager?.let { TimetableAdapter(child, activity, it, 4) }
        rvFifth?.adapter = fifth

        rvSixth?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val sixth = fragmentManager?.let { TimetableAdapter(child, activity, it, 5) }
        rvSixth?.adapter = sixth

        /*rvSeven?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL ,false)
        val seventh = fragmentManager?.let { TimetableAdapter(child, activity, it, 6) }
        rvSeven?.adapter = seventh
*/
    }

    override fun apiError() {
        try {
            Toast.makeText(activity, "error", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
        }

    }

    companion object {
    }
}
