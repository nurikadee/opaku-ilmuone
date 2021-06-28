package com.nurika.opaku.view.catalog

import android.os.Bundle
import android.view.MenuItem
import com.nurika.opaku.R
import com.nurika.opaku.data.repository.local.entity.CatalogEntity
import com.nurika.opaku.databinding.ActivityCatalogDetailBinding
import com.nurika.opaku.view.BaseActivity
import com.squareup.picasso.Picasso


class CatalogDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityCatalogDetailBinding

    private lateinit var catalogDetail: CatalogEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        screenName = "Catalog Detail Screen"

        if (intent.getSerializableExtra(KEY_CATALOG) != null) {
            catalogDetail = intent.getSerializableExtra(KEY_CATALOG) as CatalogEntity

            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)

            binding.collapsingToolbarLayout.title = catalogDetail.productName

            Picasso.get().load(catalogDetail.productImage).error(R.drawable.ic_no_photo)
                .placeholder(R.drawable.ic_no_photo)
                .into(binding.expandedImage)
        }

    }

    override fun onStart() {
        super.onStart()
        sendLog()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val KEY_CATALOG = "catalog"
    }
}