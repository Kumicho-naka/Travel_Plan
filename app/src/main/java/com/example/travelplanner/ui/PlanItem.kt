package com.example.travelplanner.ui

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.travelplanner.data.model.TravelPlan
import java.util.Locale

@Composable
fun PlanItem(plan: TravelPlan,
             buttonText: String,
             onClick: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFF8A2BE2).copy(alpha = 0.5f)),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column {
            Text(text = "${SimpleDateFormat("yyyy-MM-dd",
                Locale.getDefault()).format(plan.date)}",
                style = MaterialTheme.typography.headlineMedium)
            Text(text = "旅行先: ${plan.destination}"
                ,style = MaterialTheme.typography.headlineLarge)
        }
        Button(onClick = onClick,
            modifier = Modifier.height(64.dp)) {
            Text(buttonText)
        }
    }
}