package com.burgeon.parentapp.Circular

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
import com.study.projecthappiness.ListdestitutesPagination.CircularListAdapter
import com.study.projecthappiness.ListdestitutesPagination.CircularListViewModel


import com.study.projecthappiness.Pagination.State


import kotlinx.android.synthetic.main.fragment_circular.*


class CircularFragment : Fragment() {


    private lateinit var preferenceObj: PreferenceRequestHelper
    private var keyword: String = ""
    private var studentId: String = ""
    private var userId: String = ""
    private lateinit var viewModel: CircularListViewModel
    private lateinit var newsListAdapter: CircularListAdapter

    private lateinit var viewModelSearch: CircularListViewModel
    private lateinit var newsListAdapterSearch: CircularListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_circular, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceObj = PreferenceRequestHelper(activity)
        userId = preferenceObj.getStringValue(Constants.PRES_ID, "")
        studentId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")
        keyword = ""

        _funBeneficiary()
        initAdapter()
        initState()

        ivBack.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        tvTitle.setOnClickListener {
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
        viewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            @NonNull
            override fun <T : ViewModel> create(@NonNull modelClass: Class<T>): T {
                return CircularListViewModel(studentId, keyword, "", "") as T
            }
        }).get(CircularListViewModel::class.java!!)
    }


    private fun initAdapter() {
        newsListAdapter = CircularListAdapter { viewModel.retry() }
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
    }
}
