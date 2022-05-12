package com.burgeon.parentapp.GetExamination


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Adapter.GetExamAdapter
import com.burgeon.parentapp.Datamodels.GetExaminationList

import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_get_examination.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class ExaminationFragment : Fragment(), ExaminationView {

    private var studentId: String? = ""
    private val presenter = ExaminationPresenter(this, ExaminationInteractor())
    private var userId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_get_examination, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceObj = PreferenceRequestHelper(activity)
        userId = preferenceObj.getStringValue(Constants.PRES_ID, "")
        studentId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")

        ivBack.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        tvTitle.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        presenter.exam(studentId)
    }

    override fun showProgress() {
        progress?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress?.visibility = View.GONE

    }

    override fun ExamList(id: List<GetExaminationList>?) {
        RvExam?.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        val examresultadapter = fragmentManager?.let { GetExamAdapter(id, activity, it) }
        RvExam?.adapter = examresultadapter
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun ExamListFail() {
        try{
            Toast.makeText(activity, "please try agin later", Toast.LENGTH_SHORT).show()
        }catch (e:Exception){}

    }
}
