package com.lexmasterteam.databaseapp1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lexmasterteam.databaseapp1.data.UserDatabase
import com.lexmasterteam.databaseapp1.repository.UserRepository
import com.lexmasterteam.databaseapp1.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val reposotory: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        reposotory = UserRepository(userDao)
        readAllData = reposotory.readAllData
    }
    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            reposotory.addUser(user)
        }
    }
    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            reposotory.updateUser(user)
        }
    }
    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            reposotory.deleteUser(user)
        }
    }
    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            reposotory.deleteAllUsers()
        }
    }

}