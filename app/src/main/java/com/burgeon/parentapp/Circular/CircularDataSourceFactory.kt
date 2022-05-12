package com.study.projecthappiness.ListdestitutesPagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.burgeon.parentapp.Datamodels.CircularDetails


import io.reactivex.disposables.CompositeDisposable

class CircularDataSourceFactory(
    private val team_id: String,
    private val keyword: String,
    private val datefrom: String,
    private val dateto: String,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, CircularDetails>() {

    val newsDataSourceLiveData = MutableLiveData<CircularDataSource>()

    override fun create(): DataSource<Int, CircularDetails> {
        val newsDataSource = CircularDataSource(team_id, keyword, datefrom, dateto, compositeDisposable)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}