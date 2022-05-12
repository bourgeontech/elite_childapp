package com.study.projecthappiness.ListdestitutesPagination

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.burgeon.parentapp.Datamodels.CircularDetails

import com.study.projecthappiness.Pagination.State

import io.reactivex.disposables.CompositeDisposable

class CircularListViewModel(team_id: String, keyword: String, datefrom: String, dateto: String) : ViewModel() {

    /*  private val networkService = NetworkService.getService();*/
    var newsList: LiveData<PagedList<CircularDetails>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private var newsDataSourceFactory: CircularDataSourceFactory

    init {



        newsDataSourceFactory = CircularDataSourceFactory(team_id, keyword, datefrom, dateto, compositeDisposable)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        newsList = LivePagedListBuilder<Int, CircularDetails>(newsDataSourceFactory, config).build()
    }


    fun getState(): LiveData<State> = Transformations.switchMap<CircularDataSource,
            State>(newsDataSourceFactory.newsDataSourceLiveData, CircularDataSource::state)

    fun retry() {
        newsDataSourceFactory.newsDataSourceLiveData.value?.retry()
    }

    fun dataClear(teamId: String, keyword: String) {

        newsDataSourceFactory = CircularDataSourceFactory(teamId, keyword, "", "", compositeDisposable)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        newsList = LivePagedListBuilder<Int, CircularDetails>(newsDataSourceFactory, config).build()
    }

    fun listIsEmpty(): Boolean {
        return newsList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}