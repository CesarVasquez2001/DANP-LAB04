package org.idnp.jetpackpagingsample.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.idnp.jetpackpagingsample.entities.Countrie
import org.idnp.jetpackpagingsample.model.CountrieDao

@Database(entities = [Countrie::class], version = 1)
abstract class CountrieDatabase: RoomDatabase(){
    abstract fun countrieDao(): CountrieDao
}