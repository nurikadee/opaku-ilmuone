package com.nurika.opaku.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.nurika.opaku.ContextProviders
import com.nurika.opaku.utils.EnumStatus
import com.nurika.opaku.utils.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

abstract class NetworkBoundResource<ResultType, RequestType>
constructor(private val contextProviders: ContextProviders) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        setValue(Resource.loading(null))

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data))
                fetchFromNetwork(dbSource)
            else {
                result.addSource(dbSource) { newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    private fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<Resource<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {

        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response.Status) {
                EnumStatus.SUCCESS ->
                    GlobalScope.launch(contextProviders.IO) {
                        saveCallResult(response.data!!)

                        GlobalScope.launch(contextProviders.Main){
                            result.addSource(loadFromDB()) { newData ->
                                setValue(Resource.success(newData))
                            }
                        }
                    }
                EnumStatus.LOADING -> GlobalScope.launch(contextProviders.Main) {
                    result.addSource(loadFromDB()) { newData ->
                        setValue(Resource.success(newData))
                    }
                }
                EnumStatus.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        setValue(Resource.error(response.message!!, newData))
                    }
                }
            }
        }
    }

    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}