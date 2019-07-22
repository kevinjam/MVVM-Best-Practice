package com.kevinjanvier.understandmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kevinjanvier.understandmvvm.data.db.entities.User


@Database(
    entities = [User::class],
    version = 1
)
abstract class AppDatabase :RoomDatabase(){

    abstract fun getUserDao():UserDao


    //create app db
    companion object{

        @Volatile // mean this variable is visible to other thread
        private var instance :AppDatabase?= null
        private var LOCK = Any()


        operator fun invoke(context:Context) = instance ?: synchronized(LOCK){
            instance?:buildDatabase(context)
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,
                "Mydatabase.db"
            ).build()

    }
}