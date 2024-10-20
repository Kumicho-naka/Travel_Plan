package com.example.travelplanner.viewModelInterface

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

data class MainScreenData (
    val navigateToPlanCreate: () -> Unit,
    val navigateToPlanResult: () -> Unit,
    val navigateToPlanDelete: () -> Unit,
)

/**
 * プレビュー用
 */
class FakeMainDataProvider : PreviewParameterProvider<MainScreenData> {
    override val values: Sequence<MainScreenData> = sequenceOf(
        MainScreenData(
            navigateToPlanCreate = {},
            navigateToPlanResult = {},
            navigateToPlanDelete = {}
        )
    )
}