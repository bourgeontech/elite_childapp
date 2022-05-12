package com.burgeon.parentapp.Replaylist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Adapter.ReplyAdapter
import com.burgeon.parentapp.Datamodels.Replaymain

import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_replaylist.*
import java.lang.Exception


class ReplaylistFragment : Fragment(), ReplaylistView {

    private var contentId: String? = ""
    private var childId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    private val presenter: ReplaylistPresenter = ReplaylistPresenter(
        this,
        ReplaylistInteractor()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contentId = it.getString("contentId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_replaylist, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceObj = PreferenceRequestHelper(activity)
        childId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")

        ivBackicon.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        tvTitletext.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        presenter.getStudentprofile(contentId!!)
    }

    override fun showProgress() {
        pgProgreebar?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pgProgreebar?.visibility = View.GONE
    }

    override fun setProfileData(child: Replaymain?) {
        try {
            rvReplaylist.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            val adapter = ReplyAdapter(activity, fragmentManager, child?.data!!)
            rvReplaylist.adapter = adapter
        } catch (e: Exception) {
        }
    }

    override fun apiError(msg: String?) {
        try {
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(
            chapterId: String
        ) =
            ReplaylistFragment().apply {
                arguments = Bundle().apply {
                    putString("contentId", chapterId)
                }
            }
    }

}
