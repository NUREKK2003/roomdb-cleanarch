package com.lexmasterteam.databaseapp1.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lexmasterteam.databaseapp1.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    // gdy chcesz użyć niezaprogramowanej wcześniej funkci skorzystaj z @Query
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUser()
}