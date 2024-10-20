package com.example.travelplanner.di

import android.content.Context
import androidx.room.Room
import com.example.travelplanner.data.database.TravelPlanDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModules {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): TravelPlanDatabase {
        return Room.databaseBuilder(appContext, TravelPlanDatabase::class.java, "travel_plan_database")
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideTravelPlanDao(database: TravelPlanDatabase) = database.travelPlanDao()
}