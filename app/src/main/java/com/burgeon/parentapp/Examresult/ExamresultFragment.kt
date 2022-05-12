package com.burgeon.parentapp.Examresult


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.burgeon.parentapp.Adapter.ExamresultAdapter
import com.burgeon.parentapp.Datamodels.ExamresultdetailsDatamodel

import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_examresult.*
import java.lang.Exception


class ExamresultFragment : Fragment(), ExamresultView {

    private var studentId: String? = ""
    private var userId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    private val presenter = ExamresultPresenter(this, ExamresultInteractor())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_examresult, container, false)
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

        getExamresult()
    }

    private fun getExamresult() {
        presenter.getExamData(studentId)
    }


    override fun showProgress() {
        pgprogress?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pgprogress?.visibility = View.GONE
    }

    override fun apiError() {
        try{
            Toast.makeText(activity, "Please try later", Toast.LENGTH_LONG).show()
        }catch (e:Exception){
        }

    }

    override fun setExamResult(data: List<ExamresultdetailsDatamodel>?) {

        rvExamresult?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val examresultadapter = fragmentManager?.let { ExamresultAdapter(data, activity, it) }
        rvExamresult?.adapter = examresultadapter
    }

    companion object {
    }
}
