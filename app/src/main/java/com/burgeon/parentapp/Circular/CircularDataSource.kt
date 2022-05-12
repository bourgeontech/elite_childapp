package com.study.projecthappiness.ListdestitutesPagination


import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.burgeon.parentapp.Datamodels.Circular
import com.burgeon.parentapp.Datamodels.CircularDetails


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

class CircularDataSource(
    private val student_id: String,
    private val keyword: String,
    private val datefrom: String,
    private val dateto: String,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, CircularDetails>() {

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CircularDetails>
    ) {
        updateState(State.LOADING)

        val call: Call<Circular> = ApiClient.getClient.getCircular(
            "1", "10",student_id
        )

        call.enqueue(object : Callback<Circular> {
            override fun onFailure(call: Call<Circular>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<Circular>,
                response: Response<Circular>
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
        callback: LoadCallback<Int, CircularDetails>
    ) {
        updateState(State.LOADING)


        val start = params.key;

        val call: Call<Circular> = ApiClient.getClient.getCircular(
            start.toString(), "10"
        ,student_id)

        call.enqueue(object : Callback<Circular> {
            override fun onFailure(call: Call<Circular>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<Circular>,
                response: Response<Circular>
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
        callback: LoadCallback<Int, CircularDetails>
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