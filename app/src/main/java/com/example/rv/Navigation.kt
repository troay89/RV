package com.example.rv

import com.example.rv.model.User

interface Navigation {

    fun showDetails(user: User)

    fun goBack()

    fun toast(messageRest: Int)
}