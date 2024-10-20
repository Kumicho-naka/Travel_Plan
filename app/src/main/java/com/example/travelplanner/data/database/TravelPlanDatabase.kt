package com.example.travelplanner.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.travelplanner.data.dao.TravelPlanDao
import com.example.travelplanner.data.model.TravelPlan

@Database(entities = [TravelPlan::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TravelPlanDatabase : RoomDatabase() {
    abstract fun travelPlanDao(): TravelPlanDao
}