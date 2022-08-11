package com.example.a7minutesworkout

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper


@Database(entities = [HistoryEntity::class], version = 1)
abstract class HistoryDatabase: RoomDatabase(){
    /**
     * Connects the database to the DAO.
     */
    abstract fun historyDao():HistoryDao
    /*
    * Define a companion object, this allows us to add functions on the EmployeeDatabase class.
    * For example, classes can call
    * */
    companion object {
        @Volatile
        private var INSTANCE: HistoryDatabase?=null
        /**
         * Helper function to get the database.
         *
         * If a database has already been retrieved, the previous database will be returned.
         * Otherwise, create a new database.
         *
         * This function is threadsafe, and callers should cache the result for multiple database
         * calls to avoid overhead.
         *
         * This is an example of a simple Singleton pattern that takes another Singleton as an
         * argument in Kotlin.
         *
         * To learn more about Singleton read the wikipedia article:
         * https://en.wikipedia.org/wiki/Singleton_pattern
         *
         * @param context The application context Singleton, used to get access to the filesystem.
         */
        fun getInstance(context: Context):HistoryDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryDatabase::class.java,
                        "history-table"
                    )
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // Migration is not part of this lesson. You can learn more about
                        // migration with Room in this blog post:
                        // https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
                        .fallbackToDestructiveMigration()
                        .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }



}