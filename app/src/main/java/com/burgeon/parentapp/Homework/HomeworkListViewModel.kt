package com.burgeon.parentapp.ListdestitutesPagination

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.burgeon.parentapp.Datamodels.HomeworkDetailsmodel
import com.study.projecthappiness.Pagination.State

import io.reactivex.disposables.CompositeDisposable

class HomeworkListViewModel(team_id: String, keyword: String, datefrom: String, dateto: String) : ViewModel() {

    /*  private val networkService = NetworkService.getService();*/
    var newsList: LiveData<PagedList<HomeworkDetailsmodel>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private var newsDataSourceFactory: HomeworkDataSourceFactory

    init {

        newsDataSourceFactory = HomeworkDataSourceFactory(team_id, keyword, datefrom, dateto, compositeDisposable)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        newsList = LivePagedListBuilder<Int, HomeworkDetailsmodel>(newsDataSourceFactory, config).build()
    }


    fun getState(): LiveData<State> = Transformations.switchMap<HomeworkDataSource,
            State>(newsDataSourceFactory.newsDataSourceLiveData, HomeworkDataSource::state)

    fun retry() {
        newsDataSourceFactory.newsDataSourceLiveData.value?.retry()
    }

    fun dataClear(teamId: String, keyword: String) {

        newsDataSourceFactory = HomeworkDataSourceFactory(teamId, keyword, "", "", compositeDisposable)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        newsList = LivePagedListBuilder<Int, HomeworkDetailsmodel>(newsDataSourceFactory, config).build()
    }

    fun listIsEmpty(): Boolean {
        return newsList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}