package com.nurika.opaku.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nurika.opaku.ContextProviders
import com.nurika.opaku.data.NetworkBoundResource
import com.nurika.opaku.data.repository.local.LocalDataSource
import com.nurika.opaku.data.repository.local.entity.CatalogEntity
import com.nurika.opaku.data.repository.local.entity.UserEntity
import com.nurika.opaku.data.repository.remote.RemoteDataSource
import com.nurika.opaku.data.repository.remote.json.catalog.CatalogApi
import com.nurika.opaku.data.repository.remote.json.user.UserApi
import com.nurika.opaku.utils.Resource

class AppRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val contextProviders: ContextProviders
) {


    companion object {
        @Volatile
        private var instance: AppRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(
                    remoteDataSource,
                    localDataSource,
                    ContextProviders.getInstance()
                )
                    .also { instance = it }
            }
    }


    fun getUser(): LiveData<Resource<PagedList<UserEntity>>> {

        return object : NetworkBoundResource<PagedList<UserEntity>, UserApi>(contextProviders) {
            public override fun loadFromDB(): LiveData<PagedList<UserEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllUser(), config).build()
            }

            override fun shouldFetch(data: PagedList<UserEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall() = remoteDataSource.getUser()

            public override fun saveCallResult(data: UserApi) {
                for (itm in data.results) {
                    val user = itm.fullName?.let { UserEntity(itm.userId, itm.pictureUrl, it) }
                    localDataSource.insertUser(user!!)
                }

            }
        }.asLiveData()

    }

    fun getCatalog(): LiveData<Resource<PagedList<CatalogEntity>>> {

        return object :
            NetworkBoundResource<PagedList<CatalogEntity>, CatalogApi>(contextProviders) {
            public override fun loadFromDB(): LiveData<PagedList<CatalogEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllCatalog(), config).build()
            }

            override fun shouldFetch(data: PagedList<CatalogEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall() = remoteDataSource.getCatalog()

            public override fun saveCallResult(data: CatalogApi) {
                for (itm in data.results) {
                    val catalog = itm.productName?.let {
                        itm.productDesc?.let { desc ->
                            CatalogEntity(
                                itm.productId,
                                itm.productImage,
                                it,
                                desc,
                                itm.productPrice,
                                itm.productSale,
                                itm.productDiscountPercent
                            )
                        }
                    }
                    localDataSource.insertCatalog(catalog!!)
                }
            }
        }.asLiveData()
    }

}