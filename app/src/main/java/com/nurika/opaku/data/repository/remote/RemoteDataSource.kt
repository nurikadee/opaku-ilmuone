package com.nurika.opaku.data.repository.remote


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nurika.opaku.ContextProviders
import com.nurika.opaku.data.repository.remote.api.CatalogService
import com.nurika.opaku.data.repository.remote.api.UserService
import com.nurika.opaku.data.repository.remote.json.RetrofitFactory
import com.nurika.opaku.data.repository.remote.json.catalog.CatalogApi
import com.nurika.opaku.data.repository.remote.json.user.UserApi
import com.nurika.opaku.utils.CatalogDataDummy
import com.nurika.opaku.utils.Resource
import com.nurika.opaku.utils.UserDataDummy
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RemoteDataSource(private val contextProviders: ContextProviders) {

    private val strApi = "https://localhost/rg/"
    private val apiKey = "bcfe8d2c5ce0d14c7d32c648a1b3679d"
    private val page = "1"

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(contextProviders: ContextProviders): RemoteDataSource =
            instance
                ?: synchronized(this) {
                    instance
                        ?: RemoteDataSource(contextProviders)
                }
    }

    private fun getUserApi(): UserService =
        RetrofitFactory.selectClass(strApi, UserService::class.java) as UserService

    private suspend fun fetchUserApi(): UserApi = getUserApi().fetchUser(apiKey, page)

    fun getUser(): LiveData<Resource<UserApi>> {

        val resultContent = MutableLiveData<Resource<UserApi>>()
        GlobalScope.launch(contextProviders.IO) {
            try {
                resultContent.postValue(
                    Resource.success(
                        //fetchUserApi()
                        UserDataDummy.simulateApiUser()
                    )
                )
            } catch (ex: Exception) {
                Resource.error(ex.message.toString(), null)
            }
        }

        return resultContent
    }

    private fun getCatalogApi(): CatalogService =
        RetrofitFactory.selectClass(strApi, CatalogService::class.java) as CatalogService

    private suspend fun fetchCatalogApi(): CatalogApi = getCatalogApi().fetchCatalog(apiKey, page)

    fun getCatalog(): LiveData<Resource<CatalogApi>> {

        val resultContent = MutableLiveData<Resource<CatalogApi>>()
        GlobalScope.launch(contextProviders.IO) {
            try {
                resultContent.postValue(
                    Resource.success(
                        //fetchCatalogApi()
                        CatalogDataDummy.simulateApiCatalog()
                    )
                )
            } catch (ex: Exception) {
                Resource.error(ex.message.toString(), null)
            }
        }

        return resultContent
    }

}