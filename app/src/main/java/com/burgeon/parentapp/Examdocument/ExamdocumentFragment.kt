package com.burgeon.parentapp.Homework

import android.os.Bundle
import android.os.StrictMode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.ListdestitutesPagination.NewsListAdapter
import com.burgeon.parentapp.ListdestitutesPagination.NewsListViewModel
import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper


import com.study.projecthappiness.Pagination.State


import kotlinx.android.synthetic.main.fragment_homework.*


class ExamdocumentFragment : Fragment() {

    private var childIdSectionid: String? = ""
    private var childIdClassid: String? = ""
    private var childId: String? = ""
    private var userName: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    private var keyword: String = ""
    private var teamId: String = ""
    private var userId: String = ""
    private lateinit var viewModel: NewsListViewModel
    private lateinit var newsListAdapter: NewsListAdapter

    private lateinit var viewModelSearch: NewsListViewModel
    private lateinit var newsListAdapterSearch: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_examdoc, container, false)

        preferenceObj = PreferenceRequestHelper(activity)
        userId = preferenceObj.getStringValue(Constants.PRES_ID, "")
        userName = preferenceObj.getStringValue(Constants.PRES_NAME, "")
        childId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")
        childIdClassid = preferenceObj.getStringValue(Constants.PRES_STUDENT_CLASS_ID, "")
        childIdSectionid = preferenceObj.getStringValue(Constants.PRES_STUDENT_SECTION_ID, "")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        keyword = ""

        _funBeneficiary()
        initAdapter()
        initState()

        tvTitle.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        ivBack.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        /*ivSearch.setOnClickListener {

            keyword = edtSearch.text.toString()
            rvpendingdestitutes.adapter = null

            viewModel.retry()

            Toast.makeText(activity,"hhhhh",Toast.LENGTH_LONG).show()

            *//*_funBeneficiarySearch()
            initAdapterSearch()
            initStateSearch()*//*

           *//* if(edtSearch.text.toString()=="" || edtSearch.text.toString().length==0)
            {
                initAdapter()
                initState()
            }*//*

        }*/
    }

    private fun _funBeneficiary() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            @NonNull
            override fun <T : ViewModel> create(@NonNull modelClass: Class<T>): T {
                return NewsListViewModel(childIdClassid!!, childIdSectionid!!, "", "") as T
            }
        }).get(NewsListViewModel::class.java!!)
    }


    private fun initAdapter() {
        newsListAdapter = NewsListAdapter { viewModel.retry() }
        rvpendingdestitutes.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rvpendingdestitutes.adapter = newsListAdapter
        viewModel.newsList.observe(this, Observer {
            newsListAdapter.submitList(it)
        })
    }

    private fun initState() {

        /* ivSearch.setOnClickListener {

             keyword = edtSearch.text.toString()

             rvpendingdestitutes.adapter = null
             viewModel.dataClear(teamId, keyword)
             initAdapter()
             initState()

         }*/

        txt_error.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(viewLifecycleOwner, Observer { state ->
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
    }
}
