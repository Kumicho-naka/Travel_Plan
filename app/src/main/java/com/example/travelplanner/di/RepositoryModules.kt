package com.example.travelplanner.di

import com.example.travelplanner.repository.TravelPlanRepository
import com.example.travelplanner.repository.TravelPlanRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModules {
    @Binds
    @Singleton
    abstract fun provideTravelPlanRepository(
        travelPlanRepository: TravelPlanRepositoryImpl
    ): TravelPlanRepository
}