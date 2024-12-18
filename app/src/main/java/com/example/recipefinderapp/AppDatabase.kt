package com.example.recipefinderapp

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class], exportSchema = false, version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDao(): Dao
}