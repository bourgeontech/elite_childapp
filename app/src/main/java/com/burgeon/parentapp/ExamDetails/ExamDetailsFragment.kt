package com.burgeon.parentapp.ExamDetails


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Adapter.ExamDetailsAdapter
import com.burgeon.parentapp.Datamodels.ExamDetailsList

import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_exam_details.*
import kotlinx.android.synthetic.main.fragment_exam_details.progress
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class ExamDetailsFragment : Fragment(), ExamDetailsView {
    private val presenter = ExamDetailsPresenter(this, ExamDetailsInteractor())
    private var rowid: String = ""
    private var studentId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_exam_details, container, false)
        rowid = arguments?.getString("id") as String
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceObj = PreferenceRequestHelper(activity)
        studentId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")
        presenter.exam(rowid, studentId!!)

        ivBack.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        tvTitle.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
    }

    companion object {

        fun newInstance(
            id: String
        ): ExamDetailsFragment {

            val args = Bundle()
            args.putString("id", id)

            val fragment = ExamDetailsFragment()
            fragment.arguments = args
            return fragment
        }

    }


    override fun showProgress() {
        progress?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress?.visibility = View.GONE
    }

    override fun ExamList(id: List<ExamDetailsList>?) {
        rvExamDetails?.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        val examresultadapter = fragmentManager?.let { ExamDetailsAdapter(id, activity, it) }
        rvExamDetails?.adapter = examresultadapter
    }

    override fun ExamListFail() {
        try{
            Toast.makeText(activity, "Please try Later", Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
        }

    }
}
