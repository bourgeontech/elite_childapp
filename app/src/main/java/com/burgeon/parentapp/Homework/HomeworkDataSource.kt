package com.burgeon.parentapp.ListdestitutesPagination


import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.burgeon.parentapp.Datamodels.HomeworkDatamodel
import com.burgeon.parentapp.Datamodels.HomeworkDetailsmodel

import com.study.firebasecrash.Retrofit.ApiClient
import com.study.projecthappiness.Pagination.State

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeworkDataSource(
    private val classid: String,
    private val sectionid: String,
    private val datefrom: String,
    private val dateto: String,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, HomeworkDetailsmodel>() {

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, HomeworkDetailsmodel>
    ) {
        updateState(State.LOADING)

        val call: Call<HomeworkDatamodel> = ApiClient.getClient.getHomework(
            "1", "10", classid, sectionid
        )

        call.enqueue(object : Callback<HomeworkDatamodel> {
            override fun onFailure(call: Call<HomeworkDatamodel>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<HomeworkDatamodel>,
                response: Response<HomeworkDatamodel>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        updateState(State.DONE)
                        callback.onResult(response.body()?.data!!, null, 2)
                    } else {
                        updateState(State.DONE)
                    }
                }

            }

        })


    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, HomeworkDetailsmodel>
    ) {
        updateState(State.LOADING)


        val start = params.key;

        val call: Call<HomeworkDatamodel> = ApiClient.getClient.getHomework(
            start.toString(), "10", classid, sectionid
        )

        call.enqueue(object : Callback<HomeworkDatamodel> {
            override fun onFailure(call: Call<HomeworkDatamodel>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<HomeworkDatamodel>,
                response: Response<HomeworkDatamodel>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        updateState(State.DONE)
                        callback.onResult(response.body()?.data!!, params.key + 1)
                    } else {
                        updateState(State.DONE)
                    }
                }


            }

        })


    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, HomeworkDetailsmodel>
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun updateState(state: State) {
        this.state.postValue(state);
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            )
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }


}