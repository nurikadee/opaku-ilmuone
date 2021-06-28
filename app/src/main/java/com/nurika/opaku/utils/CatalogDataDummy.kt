package com.nurika.opaku.utils

import com.nurika.opaku.data.repository.remote.json.catalog.CatalogApi
import com.nurika.opaku.data.repository.remote.json.catalog.ResultCatalog

object CatalogDataDummy {

    fun simulateApiCatalog(): CatalogApi {

        val listCatalog = mutableListOf<ResultCatalog>()

        listCatalog.add(
            ResultCatalog(
                1,
                "https://images.unsplash.com/photo-1523275335684-37898b6baf30?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8cHJvZHVjdHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80",
                "Jam Tangan Black and White",
                "Jam Tangan dengan motif lucu berwarna hitam dan putih",
                2000000,
                500000,
                75
            )
        )
        listCatalog.add(
            ResultCatalog(
                2,
                "https://www-file.huawei.com/-/media/corp2020/home/cbg/0602/freebuds_4_cn_m.jpg",
                "Buds",
                "Buds berwarna hitam dan putih",
                2000000,
                0,
                0
            )
        )
        listCatalog.add(
            ResultCatalog(
                3,
                "https://images.pexels.com/photos/90946/pexels-photo-90946.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "Camera DSLR",
                "Camera DSLR berwarna hitam dan putih",
                2000000,
                1800000,
                10
            )
        )
        listCatalog.add(
            ResultCatalog(
                4,
                "https://fdn.gsmarena.com/imgroot/news/21/04/iphone-13-product-red-renders/-1200/gsmarena_002.jpg",
                "iPhone 13",
                "iPhone 13 limited edition",
                10000000,
                3000000,
                70
            )
        )
        listCatalog.add(
            ResultCatalog(
                5,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZPDVe_DSjpRjDk1F0jbSShwEEFXagGvQgieYqfLfmpCNI5KyjolBhetVCWwE7VRy0WzU&usqp=CAU",
                "Headset",
                "Headset lucu warna warni",
                2000000,
                500000,
                75
            )
        )
        listCatalog.add(
            ResultCatalog(
                6,
                "https://images.unsplash.com/photo-1523275335684-37898b6baf30?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8cHJvZHVjdHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80",
                "Jam Tangan Black and White",
                "Jam Tangan dengan motif lucu berwarna hitam dan putih",
                2000000,
                500000,
                75
            )
        )
        listCatalog.add(
            ResultCatalog(
                7,
                "https://images.unsplash.com/photo-1523275335684-37898b6baf30?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8cHJvZHVjdHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80",
                "Jam Tangan Black and White",
                "Jam Tangan dengan motif lucu berwarna hitam dan putih",
                100000,
                50000,
                50
            )
        )
        listCatalog.add(
            ResultCatalog(
                8,
                "https://images.unsplash.com/photo-1523275335684-37898b6baf30?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8cHJvZHVjdHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80",
                "Jam Tangan Black and White",
                "Jam Tangan dengan motif lucu berwarna hitam dan putih",
                1000000,
                0,
                0
            )
        )
        listCatalog.add(
            ResultCatalog(
                9,
                "https://images.unsplash.com/photo-1523275335684-37898b6baf30?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8cHJvZHVjdHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80",
                "Jam Tangan Black and White",
                "Jam Tangan dengan motif lucu berwarna hitam dan putih",
                100000,
                0,
                0
            )
        )
        listCatalog.add(
            ResultCatalog(
                10,
                "https://images.unsplash.com/photo-1523275335684-37898b6baf30?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8cHJvZHVjdHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80",
                "Jam Tangan Black and White",
                "Jam Tangan dengan motif lucu berwarna hitam dan putih",
                2000000,
                500000,
                75
            )
        )

        return CatalogApi(1, listCatalog, 1, 10)
    }
}