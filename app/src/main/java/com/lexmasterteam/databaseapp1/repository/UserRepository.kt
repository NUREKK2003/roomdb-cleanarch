package com.lexmasterteam.databaseapp1.repository

import androidx.lifecycle.LiveData
import com.lexmasterteam.databaseapp1.data.UserDao
import com.lexmasterteam.databaseapp1.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }
    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }
    suspend fun deleteAllUsers(){
        userDao.deleteAllUser()
    }

}