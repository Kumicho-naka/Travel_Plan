package com.example.travelplanner.viewModelInterface

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.travelplanner.data.model.TravelPlan

data class PlanResultData (
    val updatePlan:(TravelPlan) -> Unit,
    val plans:List<TravelPlan>,
    val navigateToMain: () -> Unit,
    val navigateToWebView: (String) -> Unit
    )


/**
 * プレビュー用
 */
class FakePlanUpdateDataProvider : PreviewParameterProvider<PlanResultData> {
    override val values: Sequence<PlanResultData> = sequenceOf(
        PlanResultData(
            plans = listOf(),
            updatePlan = {},
            navigateToMain = {},
            navigateToWebView = {}
        )
    )
}