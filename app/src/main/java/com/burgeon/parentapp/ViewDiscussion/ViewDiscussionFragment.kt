package com.burgeon.parentapp.ViewDiscussion


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper

import com.study.projecthappiness.ListdestitutesPagination.*
import com.study.projecthappiness.Pagination.State
import kotlinx.android.synthetic.main.fragment_view_discussion.*


/**
 * A simple [Fragment] subclass.
 */
class ViewDiscussionFragment : Fragment() {

    private var contentId: String? = ""
    private var userid: String = ""
    private lateinit var viewModel: discussionListViewModel
    private lateinit var newsListAdapter: discussionListAdapter


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
        val view: View = inflater.inflate(R.layout.fragment_view_discussion, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivBackdiscussion.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        tvTitlediscussion.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        var preferenceObj = PreferenceRequestHelper(activity)
        userid = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")

        _funBeneficiary()
        initAdapter()
        initState()
    }

    private fun _funBeneficiary() {
        viewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            @NonNull
            override fun <T : ViewModel> create(@NonNull modelClass: Class<T>): T {
                return discussionListViewModel(contentId!!) as T
            }
        }).get(discussionListViewModel::class.java!!)
    }


    private fun initAdapter() {
        newsListAdapter = discussionListAdapter({ viewModel.retry() }, contentId!!)
        rvpendingdestitutes.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rvpendingdestitutes.adapter = newsListAdapter
        viewModel.newsList.observe(this, Observer {
            newsListAdapter.submitList(it)
        })
    }

    private fun initState() {

        txt_error.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(this, Observer { state ->
            progress_bar.visibility =
                if (viewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility =
                if (viewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            if (!viewModel.listIsEmpty()) {
                newsListAdapter.setState(state ?: State.DONE)
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(
            chapterId: String
        ) =
            ViewDiscussionFragment().apply {
                arguments = Bundle().apply {
                    putString("contentId", chapterId)
                }
            }
    }

}
