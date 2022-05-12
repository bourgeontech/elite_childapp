package com.burgeon.parentapp.Remark


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Adapter.RemarkAdapter
import com.burgeon.parentapp.Datamodels.RemarkDetails

import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_remark.*
import kotlinx.android.synthetic.main.fragment_remark.progress

class RemarkFragment : Fragment(), RemarkView {

    private var studentId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    private val presenter = RemarkPresenter(this, RemarkInteractor())
    private lateinit var ondelete:RemarkAdapter.RemarkDelete
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_remark, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceObj = PreferenceRequestHelper(activity)
        studentId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")
        ondelete=object :RemarkAdapter.RemarkDelete{
            override fun onclicked(id: String) {
              presenter.delete(id)
            }

        }

        ivBack.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        tvTitle.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        presenter.remark(studentId)
    }

    override fun showProgress() {
        progress?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress?.visibility = View.GONE
    }

    override fun RemarkListFail() {
        Toast.makeText(activity, "please try later", Toast.LENGTH_SHORT).show()
    }

    override fun RemarkList(id: List<RemarkDetails>?) {
        rvRemark.adapter=null
        rvRemark?.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        val examresultadapter = fragmentManager?.let { RemarkAdapter(id, activity, it,ondelete) }
        rvRemark?.adapter = examresultadapter
    }

    override fun RemarkDelete(message: String) {
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show()
        presenter.remark(studentId)
    }

    override fun RemarkDeleteFail(message: String) {
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}
