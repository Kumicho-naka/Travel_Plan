package com.example.travelplanner.ui

import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.travelplanner.R
import com.example.travelplanner.data.model.TravelPlan
import com.example.travelplanner.util.CoreUtil.ldtToDate
import com.example.travelplanner.util.CoreUtil.resetTodayWithResetTIme
import com.example.travelplanner.viewModelInterface.FakePlanCreateDataProvider
import com.example.travelplanner.viewModelInterface.PlanCreateData
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanCreateScreen(planCreateData: PlanCreateData){
    val context = LocalContext.current

    val calendar = Calendar.getInstance()

    /**
    * 比較用
    **/
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    var selectedDate by remember { mutableStateOf(calendar.time) }
    var destination by remember { mutableStateOf("") }

    var destinationError by remember { mutableStateOf(false) }

    var showDatePickDialog by remember { mutableStateOf(false) }
    var showSaveDialog by remember { mutableStateOf(false) }
    var showCancelDialog by remember { mutableStateOf(false) }

    AppScreenWithHeader(
        title = "プラン作成",
        onBackClick = { planCreateData.navigateToMain() }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.colcbord_free),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.5F
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate),
                    onValueChange = {},
                    label = { Text("日付") },
                    readOnly = true,
                    modifier = Modifier,
                    trailingIcon = {
                        IconButton(onClick = { showDatePickDialog = !showDatePickDialog }) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "日付選択"
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = destination,
                    onValueChange =
                    {
                        destination = it
                        destinationError = destination.isEmpty()
                    },
                    isError =  destinationError,
                    label = { Text("旅行先") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if(destination.isEmpty()){
                            destinationError = true
                        }else{
                            val newPlan = TravelPlan(
                                date = selectedDate,
                                destination = destination
                            )
                            planCreateData.addPlan(newPlan)
                            showSaveDialog = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(64.dp)
                )
                {
                    Text(
                        "プラン保存",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (dateFormat.format(selectedDate) != dateFormat.format(context.resetTodayWithResetTIme()) ||
                            destination.isNotEmpty()
                        ) {
                            showCancelDialog = true
                        } else {
                            planCreateData.navigateToMain()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(64.dp)
                ) {
                    Text(
                        "キャンセル",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
    if(showDatePickDialog){
        val datePickerState = rememberDatePickerState()

        DatePickerDialog(
            onDismissRequest = {showDatePickDialog=false},
            confirmButton = {
                TextButton(onClick = {
                    val instant = datePickerState.selectedDateMillis?.let { Instant.ofEpochMilli(it) }

                    selectedDate = context.ldtToDate(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()))
                    showDatePickDialog=false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = {showDatePickDialog=false}) {
                    Text("キャンセル")
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
            )
        }
    }

    /**
    * 保存後の選択肢ダイアログ
    **/
    if (showSaveDialog){

        CheckDialog(
            showDialog = showSaveDialog,
            title = "次のアクションを選択してください",
            message = "プランを確認するか, 続けてプランを作成しますか?",
            confirmLabel = "プランを確認",
            dismissLabel = "続けてプランを作成",
            onConfirm = {
                showSaveDialog = false
                planCreateData.navigateToPlanResult()
            },
            onDismiss = {
                showSaveDialog = false
                destination = ""
            }
        )
    }

    /**
    * キャンセル時のダイアログ
    **/
    if (showCancelDialog){

        CheckDialog(
            showDialog = showCancelDialog,
            title = "プランの破棄",
            message = "入力中のプランを破棄しますか?",
            confirmLabel = "はい",
            dismissLabel = "いいえ",
            onConfirm = {
                showCancelDialog = false
                planCreateData.navigateToMain()
            },
            onDismiss = { showCancelDialog = false }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PlanCreatePreview(){
    PlanCreateScreen( FakePlanCreateDataProvider().values.first() )
}