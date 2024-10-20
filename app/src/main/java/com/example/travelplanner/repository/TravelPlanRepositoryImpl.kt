package com.example.travelplanner.repository

import android.content.Context
import android.icu.util.Calendar
import com.example.travelplanner.data.dao.TravelPlanDao
import com.example.travelplanner.data.model.TravelPlan
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TravelPlanRepositoryImpl @Inject constructor(
    @ApplicationContext
    private val appContext: Context,
    private val travelPlanDao: TravelPlanDao
) :TravelPlanRepository {
    /**
     * プラン追加
     **/
    override fun addPlan(plan: TravelPlan) {
        travelPlanDao.insert(plan)
    }

    /**
     * プラン削除
     **/
    override fun deletePlan(plan: TravelPlan) {
        travelPlanDao.delete(plan)
    }

    /**
     * プラン更新
     **/
    override fun updatePlan(plan: TravelPlan) {
        travelPlanDao.update(plan)
    }

    /**
     * 全プラン取得
     **/
    override fun getAllPlans(): List<TravelPlan> {
        return travelPlanDao.getAllPlans()
    }

    /**
     * x日より前のプランを削除
     **/
    override fun deletePlanOlderYesterday() {
        // 指定の日付を取得(例: 1日前の日付を取得)
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val date = calendar.time

        // DateをLongに変換してクエリに渡す
        travelPlanDao.deletePlanOlderThan(date.time)
    }
}