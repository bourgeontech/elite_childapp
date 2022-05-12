package com.study.projecthappiness.ListdestitutesPagination

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.burgeon.parentapp.Datamodels.Questions

import com.study.projecthappiness.Pagination.State

import io.reactivex.disposables.CompositeDisposable

class discussionListViewModel(team_id: String) : ViewModel() {

    var newsList: LiveData<PagedList<Questions>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private var newsDataSourceFactory: discussionDataSourceFactory

    init {
        newsDataSourceFactory = discussionDataSourceFactory(team_id, compositeDisposable)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        newsList = LivePagedListBuilder<Int, Questions>(newsDataSourceFactory, config).build()
    }


    fun getState(): LiveData<State> = Transformations.switchMap<discussionDataSource,
            State>(newsDataSourceFactory.newsDataSourceLiveData, discussionDataSource::state)

    fun retry() {
        newsDataSourceFactory.newsDataSourceLiveData.value?.retry()
    }

    fun dataClear(teamId: String, keyword: String) {

        newsDataSourceFactory = discussionDataSourceFactory(teamId, compositeDisposable)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        newsList = LivePagedListBuilder<Int, Questions>(newsDataSourceFactory, config).build()
    }

    fun listIsEmpty(): Boolean {
        return newsList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}