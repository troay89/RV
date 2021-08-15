package com.example.rv.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rv.model.User
import com.example.rv.model.UsersListener
import com.example.rv.model.UsersService

class UsersListViewModel(private val usersService: UsersService): ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val usersListener: UsersListener = {
        _users.value = it
    }

    init{
        loadUsers()
    }

    fun loadUsers() {
        usersService.addListener(usersListener)
    }

    fun moveUser(user: User, moveBy: Int) {
        usersService.moveUser(user, moveBy)
    }

    fun deleteUser(user: User) {
        usersService.deleteUser(user)
    }

    override fun onCleared() {
        super.onCleared()
        usersService.removeListener(usersListener)
    }
}