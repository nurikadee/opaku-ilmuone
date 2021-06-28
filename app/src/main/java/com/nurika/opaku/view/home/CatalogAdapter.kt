package com.nurika.opaku.view.home

import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nurika.opaku.R
import com.nurika.opaku.data.repository.local.entity.CatalogEntity
import com.nurika.opaku.databinding.ItemLayoutCatalogBinding
import com.nurika.opaku.utils.Utils
import com.nurika.opaku.view.catalog.CatalogDetailActivity
import com.nurika.opaku.view.catalog.CatalogDetailActivity.Companion.KEY_CATALOG
import com.squareup.picasso.Picasso

class CatalogAdapter :
    PagedListAdapter<CatalogEntity, CatalogAdapter.CatalogViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CatalogEntity>() {
            override fun areItemsTheSame(oldItem: CatalogEntity, newItem: CatalogEntity): Boolean {
                return oldItem.productId == newItem.productId
            }

            override fun areContentsTheSame(
                oldItem: CatalogEntity,
                newItem: CatalogEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_catalog, parent, false)
        return CatalogViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {

        val catalog = getItem(position)
        if (catalog != null)
            holder.bind(catalog)

    }

    inner class CatalogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemLayoutCatalogBinding.bind(itemView)

        fun bind(catalog: CatalogEntity) {
            binding.tvProductName.text = catalog.productName

            if (catalog.productSale != 0) {
                binding.tvProductPrice.text = Utils.convertCurrency(catalog.productPrice)
                binding.tvProductPrice.paintFlags =
                    binding.tvProductPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                binding.tvProductSale.text = Utils.convertCurrency(catalog.productSale)
            } else {
                binding.tvProductPrice.visibility = View.INVISIBLE
                binding.tvProductSale.text = Utils.convertCurrency(catalog.productPrice)
            }

            Picasso.get().load(catalog.productImage).error(R.drawable.ic_no_photo)
                .placeholder(R.drawable.ic_no_photo)
                .into(binding.imgProductImage)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, CatalogDetailActivity::class.java)
                intent.putExtra(KEY_CATALOG, catalog)
                itemView.context.startActivity(intent)
            }
        }
    }

}