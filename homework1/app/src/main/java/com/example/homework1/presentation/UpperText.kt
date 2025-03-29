package com.example.homework1.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UpperText(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier.padding(16.dp),
        style = MaterialTheme.typography.headlineSmall,
        fontFamily = FontFamily.Default,
        fontSize = 20.sp
    )
}
