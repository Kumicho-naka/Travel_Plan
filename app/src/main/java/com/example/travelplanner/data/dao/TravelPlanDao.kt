package com.example.travelplanner.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.travelplanner.data.model.TravelPlan

@Dao
interface TravelPlanDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(travelPlanData: TravelPlan)

    @Update
    fun update(travelPlanData: TravelPlan)

    @Delete
    fun delete(travelPlanData: TravelPlan)

    @Query("DELETE FROM travelPlan WHERE date < :date")
    fun deletePlanOlderThan(date: Long)

    @Query("SELECT * FROM travelPlan")
    fun getAllPlans(): List<TravelPlan>
}