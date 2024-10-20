package com.example.travelplanner.repository

import com.example.travelplanner.data.model.TravelPlan

interface TravelPlanRepository {
    /**
    * プラン追加
    **/
    fun addPlan(plan: TravelPlan)

    /**
    * プラン削除
    **/
    fun deletePlan(plan: TravelPlan)

    /**
    * プラン更新
    **/
    fun updatePlan(plan: TravelPlan)

    /**
    * 全プラン取得
    **/
    fun getAllPlans(): List<TravelPlan>

    /**
    * x日より前のプランを削除
    **/
    fun deletePlanOlderYesterday()
}