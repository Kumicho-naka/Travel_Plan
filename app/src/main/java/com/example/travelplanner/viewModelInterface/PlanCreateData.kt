package com.example.travelplanner.viewModelInterface

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.travelplanner.data.model.TravelPlan

data class PlanCreateData(
    val addPlan:(TravelPlan) -> Unit,
    val navigateToPlanResult: () -> Unit,
    val navigateToMain: () -> Unit
)

/**
 * プレビュー用
 */
class FakePlanCreateDataProvider : PreviewParameterProvider<PlanCreateData> {
    override val values: Sequence<PlanCreateData> = sequenceOf(
        PlanCreateData(
            addPlan = {},
            navigateToPlanResult = {},
            navigateToMain = {}
        )
    )
}