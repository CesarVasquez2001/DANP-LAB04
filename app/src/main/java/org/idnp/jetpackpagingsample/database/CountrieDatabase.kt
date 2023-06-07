package org.idnp.jetpackpagingsample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.idnp.jetpackpagingsample.entities.Countrie
import org.idnp.jetpackpagingsample.model.CountrieDao

@Database(entities = [Countrie::class], version = 3)
abstract class CountrieDatabase: RoomDatabase(){
    abstract fun countrieDao(): CountrieDao

    companion object {
        private var INSTANCE: CountrieDatabase? = null

        fun getInstance(context: Context): CountrieDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CountrieDatabase::class.java,
                        "countries"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance

                }

                return instance
            }
        }
    }
}