package com.nurika.opaku.utils

import com.nurika.opaku.data.repository.remote.json.user.ResultUser
import com.nurika.opaku.data.repository.remote.json.user.UserApi


object UserDataDummy{

    fun simulateApiUser(): UserApi {
        val listUser = mutableListOf<ResultUser>()

        listUser.add(
            ResultUser(
                "test/picture_user_1.png",
                "Nurika Dewi",
                1234
            )
        )

        listUser.add(
            ResultUser(
                "test/picture_user_2.png",
                "Roby Ardianto Alvin",
                1235
            )
        )

        listUser.add(
            ResultUser(
                "test/picture_user_3.png",
                "Ar Rasyid Aulian",
                1236
            )
        )

        listUser.add(
            ResultUser(
                "test/picture_user_4.png",
                "Nurrohmah",
                1237
            )
        )


        listUser.add(
            ResultUser(
                "test/picture_user_5.png",
                "M Rizki",
                1238
            )
        )

        return UserApi(1, listUser, 1, 5)
    }

}