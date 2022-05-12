package com.study.projecthappiness.ListdestitutesPagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.burgeon.parentapp.Datamodels.Questions


import io.reactivex.disposables.CompositeDisposable

class discussionDataSourceFactory(
    private val team_id: String,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Questions>() {

    val newsDataSourceLiveData = MutableLiveData<discussionDataSource>()

    override fun create(): DataSource<Int, Questions> {
        val newsDataSource = discussionDataSource(team_id, compositeDisposable)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}