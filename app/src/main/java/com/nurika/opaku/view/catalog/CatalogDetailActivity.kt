package com.nurika.opaku.view.catalog

import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.nurika.opaku.R
import com.nurika.opaku.data.repository.local.entity.CatalogEntity
import com.nurika.opaku.databinding.ActivityCatalogDetailBinding
import com.nurika.opaku.utils.Utils
import com.nurika.opaku.view.BaseActivity
import com.squareup.picasso.Picasso


class CatalogDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityCatalogDetailBinding

    private lateinit var catalogDetail: CatalogEntity

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.getSerializableExtra(KEY_CATALOG) != null) {
            catalogDetail = intent.getSerializableExtra(KEY_CATALOG) as CatalogEntity

            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)

            binding.collapsingToolbarLayout.title = catalogDetail.productName

            Picasso.get().load(catalogDetail.productImage).error(R.drawable.ic_no_photo)
                .placeholder(R.drawable.ic_no_photo)
                .into(binding.expandedImage)

            binding.tvProductName.text = catalogDetail.productName
            binding.tvProductDesc.text = catalogDetail.productDesc + getString(R.string.lorem_ipsum)

            if (catalogDetail.productSale != 0) {
                binding.tvProductPrice.text = Utils.convertCurrency(catalogDetail.productPrice)
                binding.tvProductPrice.paintFlags =
                    binding.tvProductPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                binding.tvProductSale.text = Utils.convertCurrency(catalogDetail.productSale)
            } else {
                binding.tvProductPrice.visibility = View.INVISIBLE
                binding.tvProductSale.text = Utils.convertCurrency(catalogDetail.productPrice)
            }
        }

        sendProductImpression(catalogDetail)
    }

    override fun onStart() {
        super.onStart()
        sendLogScreen(
            "Catalog Detail Screen",
            CatalogDetailActivity::class.java.simpleName
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
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