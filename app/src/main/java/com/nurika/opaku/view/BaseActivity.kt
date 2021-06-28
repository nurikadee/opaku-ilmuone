package com.nurika.opaku.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.nurika.opaku.data.repository.local.entity.CatalogEntity

abstract class BaseActivity : AppCompatActivity() {

    private val firebaseAnalytics: FirebaseAnalytics = Firebase.analytics

    protected fun sendLogScreen(screenName: String, screenClass: String) {
        Log.d(screenName, "Sending data analytics")

        val parameters = Bundle()
        parameters.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName);
        parameters.putString(FirebaseAnalytics.Param.SCREEN_CLASS, screenName);

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, parameters)
    }

    protected fun sendEventAppOpen() {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, null)
    }

    protected fun sendProductImpressionList(listCatalog: MutableList<CatalogEntity>) {
        val items = ArrayList<Bundle>()
        listCatalog.forEachIndexed { index, catalog ->
            val product = Bundle()
            product.putString(FirebaseAnalytics.Param.ITEM_ID, catalog.productId.toString())
            product.putString(FirebaseAnalytics.Param.ITEM_NAME, catalog.productName)
            product.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "Electronic")
            product.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "Black & White")
            product.putString(FirebaseAnalytics.Param.ITEM_BRAND, "Opaku Brand")
            if (catalog.productSale > 0) {
                product.putDouble(FirebaseAnalytics.Param.PRICE, catalog.productSale.toDouble())
            } else {
                product.putDouble(FirebaseAnalytics.Param.PRICE, catalog.productPrice.toDouble())
            }
            product.putString(FirebaseAnalytics.Param.CURRENCY, "IDR")
            product.putLong(FirebaseAnalytics.Param.INDEX, index.toLong())

            items.add(product)
        }

        val ecommerceBundle = Bundle()
        ecommerceBundle.putParcelableArrayList("items", items)
        ecommerceBundle.putString(FirebaseAnalytics.Param.ITEM_LIST, "Products List")

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM_LIST, ecommerceBundle)
    }

    protected fun sendProductImpression(catalog: CatalogEntity) {
        val product = Bundle()
        product.putString(FirebaseAnalytics.Param.ITEM_ID, catalog.productId.toString())
        product.putString(FirebaseAnalytics.Param.ITEM_NAME, catalog.productName)
        product.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "Electronic")
        product.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "Black & White")
        product.putString(FirebaseAnalytics.Param.ITEM_BRAND, "Opaku Brand")
        if (catalog.productSale > 0) {
            product.putDouble(FirebaseAnalytics.Param.PRICE, catalog.productSale.toDouble())
        } else {
            product.putDouble(FirebaseAnalytics.Param.PRICE, catalog.productPrice.toDouble())
        }
        product.putString(FirebaseAnalytics.Param.CURRENCY, "IDR")

        val ecommerceBundle = Bundle()
        ecommerceBundle.putBundle("items", product)

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, ecommerceBundle)
    }
}