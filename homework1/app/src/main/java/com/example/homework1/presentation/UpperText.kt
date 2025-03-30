package com.example.homework1.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.homework1.R
import com.example.homework1.ui.res.MediumPadding

val Poppins = FontFamily(
    Font(R.font.poppins)
)

@Composable
fun UpperText(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier.padding(MediumPadding),
        style = MaterialTheme.typography.headlineSmall,
        fontFamily = Poppins,
        fontSize = 20.sp
    )
}
