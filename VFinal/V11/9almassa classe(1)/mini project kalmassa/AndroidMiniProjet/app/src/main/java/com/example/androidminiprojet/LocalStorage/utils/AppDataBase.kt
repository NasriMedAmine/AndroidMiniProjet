package com.example.androidminiprojet.LocalStorage.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.androidminiprojet.LocalStorage.dao.UserLocalDBDao
import java.io.Console

//TODO 8 "Change to a ROOMDataBase"




@Database(entities = [UserLocalDBDao::class], version = 1)
abstract class AppDataBase  : RoomDatabase() {

    //TODO 8.1 "Add the DAO"
    abstract fun UserLocalDBDaoDao(): UserLocalDBDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null


        // hedha zidou khater erreur
        private val lock = Any()

        fun getDatabase(context: Context): AppDataBase {
            if (instance == null) {
                synchronized(this) {
                    //TODO 8.2 "Build the DataBase"
                    val db = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java, "AndroidDataBase"
                    ).build()

                }
            }
            return instance!!

        //jetni erreur f star eli fih applicationContext
       }
//
//        fun getInstance(context: Context): AppDataBase {
//            synchronized(lock) {
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        AppDataBase::class.java, "MyDataBaseAndroid"
//                    ).addCallback(object : RoomDatabase.Callback() {
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            //Console.debug("database-name", "Database created")
//                            super.onCreate(db)
//                        }
//
//                        override fun onOpen(db: SupportSQLiteDatabase) {
//                            //Console.debug("database-name", "Database opened")
//                            super.onOpen(db)
//                        }
//                    })
//                        .fallbackToDestructiveMigration()
//                        .build()
//                }
//                return instance!!
//            }
//
//        }



    }
}


