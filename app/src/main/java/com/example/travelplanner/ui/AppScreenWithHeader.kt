package com.example.travelplanner.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
* 共通のヘッダー部分
**/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreenWithHeader(
    title: String,
    onBackClick:() -> Unit,
    content: @Composable () -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "戻る")
                    }
                }
            )
        }
    ){
        paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)){
            content()
        }
    }
}