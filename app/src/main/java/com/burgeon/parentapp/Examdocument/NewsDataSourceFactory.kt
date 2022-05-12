package com.burgeon.parentapp.ListdestitutesPagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.burgeon.parentapp.Datamodels.HomeworkDetailsmodel

import io.reactivex.disposables.CompositeDisposable

class NewsDataSourceFactory(
    private val team_id: String,
    private val keyword: String,
    private val datefrom: String,
    private val dateto: String,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, HomeworkDetailsmodel>() {

    val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, HomeworkDetailsmodel> {
        val newsDataSource = NewsDataSource(team_id, keyword, datefrom, dateto, compositeDisposable)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}