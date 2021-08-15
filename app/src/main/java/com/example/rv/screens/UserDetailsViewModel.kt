package com.example.rv.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rv.UserNotFoundException
import com.example.rv.model.User
import com.example.rv.model.UserDetails
import com.example.rv.model.UsersService

class UserDetailsViewModel(
    private val usersService: UsersService
) : ViewModel() {

    private var _userDetails = MutableLiveData<UserDetails>()
    val userDetails: LiveData<UserDetails> = _userDetails

    fun loadUser(userId: Long) {
        if(_userDetails.value != null) return
        try {
            _userDetails.value = usersService.getById(userId)
        }catch (e: UserNotFoundException){
            e.printStackTrace()
        }

    }

    fun deleteUser(){
        val userDetails: UserDetails = this.userDetails.value ?: return
        usersService.deleteUser(userDetails.user)
    }
}