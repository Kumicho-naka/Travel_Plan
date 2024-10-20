package com.example.travelplanner.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

/**
* プラン作成画面での保存やキャンセル時のダイアログを表示するカスタム
**/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckDialog(
    showDialog: Boolean,
    title: String,
    message: String,
    confirmLabel: String,
    dismissLabel: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
){
    if (showDialog){
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(title) },
            text = { Text(message) },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(confirmLabel)
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(dismissLabel)
                }
            }
        )
    }

}