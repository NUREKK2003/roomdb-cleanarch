package com.lexmasterteam.databaseapp1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lexmasterteam.databaseapp1.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase:RoomDatabase() {

    // podpięcie DAO do basy danych (żeby później dało się dostać do funkcji w repozytorium)
    abstract fun userDao():UserDao


    // Singleton (sprawdzenie czy instancja bazy już instnieje)
    companion object{
        @Volatile
        private var INSTANCE: UserDatabase?=null

        fun getDatabase(context: Context): UserDatabase{
            val tempInstance = INSTANCE
            if(tempInstance !=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}