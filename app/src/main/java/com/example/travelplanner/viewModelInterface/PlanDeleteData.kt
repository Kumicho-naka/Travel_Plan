package com.example.travelplanner.viewModelInterface

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.travelplanner.data.model.TravelPlan

data class PlanDeleteData (
    val removePlan:(TravelPlan) -> Unit,
    val getAllPlans: () -> List<TravelPlan>,
    val navigateToMain: () -> Unit
)


/**
 * プレビュー用
 */
class FakePlanDeleteDataProvider : PreviewParameterProvider<PlanDeleteData> {
    override val values: Sequence<PlanDeleteData> = sequenceOf(
        PlanDeleteData(
            getAllPlans = { listOf() },
            removePlan = {},
            navigateToMain = {}
        )
    )
}