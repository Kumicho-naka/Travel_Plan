package com.example.travelplanner.viewModelInterface

import com.example.travelplanner.data.model.TravelPlan

data class WebViewData(
    val updatePlan:(TravelPlan) -> Unit,
    val plans:List<TravelPlan>,
    val navigateToMain: () -> Unit,
    val navigateToResult: () -> Unit
)