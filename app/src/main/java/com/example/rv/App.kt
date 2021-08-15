package com.example.rv

import android.app.Application
import com.example.rv.model.UsersService


class App: Application() {
    val usersService = UsersService()
}