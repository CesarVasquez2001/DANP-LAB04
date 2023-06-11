package org.idnp.jetpackpagingsample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.idnp.jetpackpagingsample.entities.Country
import org.idnp.jetpackpagingsample.fillWithStartingCountries
import org.idnp.jetpackpagingsample.model.CountryDao

@Database(entities = [Country::class], version = 8)
abstract class CountryDatabase: RoomDatabase(){
    abstract fun countrieDao(): CountryDao

    companion object {
        private var INSTANCE: CountryDatabase? = null

        fun getInstance(context: Context): CountryDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CountryDatabase::class.java,
                        "DATABASE"
                    ).fallbackToDestructiveMigration()
                        .addCallback(fillWithStartingCountries(context))
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}