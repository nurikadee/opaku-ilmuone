package com.nurika.opaku.view.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.common.api.internal.ActivityLifecycleObserver.of
import com.nurika.opaku.R
import com.nurika.opaku.data.repository.local.entity.CatalogEntity
import com.nurika.opaku.data.repository.local.entity.UserEntity
import com.nurika.opaku.databinding.ActivityHomeBinding
import com.nurika.opaku.utils.EnumStatus
import com.nurika.opaku.view.BaseActivity
import com.nurika.opaku.view.user.UserAdapter
import com.nurika.opaku.view.user.UserViewModel
import com.nurika.opaku.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import java.util.EnumSet.of

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private val catalogAdapter = CatalogAdapter()
    private val banner: String =
        "https://image.freepik.com/free-vector/banners-e-commerce-promotions-with-mid-season-flash-sale-illustration-concept_106954-604.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        screenName = "Home Screen"

        viewModel =
            ViewModelProvider(this, ViewModelFactory.getInstance(this))[HomeViewModel::class.java]

        Picasso.get().load(banner).error(R.drawable.ic_no_photo)
            .placeholder(R.drawable.ic_no_photo)
            .into(binding.imgBanner)

        initAdapter(catalogAdapter)
        observeApi()
    }


    override fun onStart() {
        super.onStart()
        sendLog()
    }

    private fun observeApi() {
        viewModel.catalogList().observe(this, {

            when (it.Status) {
                EnumStatus.SUCCESS -> {
                    updateAdapterData(it.data, catalogAdapter)
                    binding.pbLoading.visibility = View.GONE
                }
                EnumStatus.LOADING -> binding.pbLoading.visibility = View.VISIBLE
                EnumStatus.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    binding.pbLoading.visibility = View.GONE
                }
            }

        })
    }

    private fun updateAdapterData(
        newData: PagedList<CatalogEntity>?,
        catalogAdapter: CatalogAdapter
    ) {
        newData?.let { datas ->
            catalogAdapter.submitList(datas)
            catalogAdapter.notifyDataSetChanged()
        }
    }

    private fun initAdapter(catalogAdapter: CatalogAdapter) {
        binding.rvListMenu.apply {
            adapter = catalogAdapter
            layoutManager = GridLayoutManager(this@HomeActivity, 3)
            setHasFixedSize(true)
        }
    }
}