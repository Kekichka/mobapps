package com.example.homework1.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import com.example.homework1.ui.res.*

@Composable
fun DropdownMenu() {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Select an option") }

    Box(
        modifier = Modifier
            .padding(MediumPadding)
            .clip(RoundedCornerShape(RoundMedium))
            .height(48.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(RoundSmall))
            .clickable { expanded = !expanded }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MediumPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = selectedOption,
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge
            )

            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "Dropdown Arrow",
                modifier = Modifier.size(24.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .border(1.dp, Color.Gray, RoundedCornerShape(RoundMedium))
        ) {
            DropdownMenuItem(
                text = { Text("7777777777") },
                onClick = {
                    selectedOption = "7777777777"
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("9999999999") },
                onClick = {
                    selectedOption = "9999999999"
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("444444444444") },
                onClick = {
                    selectedOption = "444444444444"
                    expanded = false
                }
            )
        }
    }
}
