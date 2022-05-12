package com.burgeon.parentapp.OnlineClass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.burgeon.parentapp.Adapter.ClassAdapter
import com.burgeon.parentapp.Datamodels.MyConference
import com.burgeon.parentapp.Datamodels.OnlineClassPojo
import com.burgeon.parentapp.Datamodels.childdetailsDatamodel
import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_communication.*
import kotlinx.android.synthetic.main.fragment_online_class.*
import kotlinx.android.synthetic.main.fragment_online_class.ivBack
import kotlinx.android.synthetic.main.fragment_online_class.tvTitle


class OnlineClassFragment : Fragment(), OnlineView {

    private val presenter = OnlinePresenter(this, OnlineInteractor())
    private var childId: String = ""
    private lateinit var preferenceObj: PreferenceRequestHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_online_class, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceObj = PreferenceRequestHelper(activity)
        childId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")
        presenter.getClass(childId)

        ivBack.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        tvTitle.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

    }

    override fun showProgress() {
        pgprogress?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pgprogress?.visibility = View.GONE
    }

    override fun setClassData(conferences: List<MyConference>) {
        rvClass?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val classadapter =
            ClassAdapter(conferences, activity, fragmentManager)
        rvClass?.adapter = classadapter
    }

    override fun serFailure() {
        Toast.makeText(activity, "Please try later", Toast.LENGTH_LONG).show()
    }


}