package com.example.homework2.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homework2.ui.theme.*

@Composable
fun NoteDetailScreen(navController: NavController, title: String, text: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MediumPadding),
    ) {
        Text(text = title, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(SmallPadding))
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(MediumPadding))

        FloatingActionButton(
            onClick = { navController.popBackStack() },
            containerColor = PookieGreen,
            contentColor = White,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(100.dp),
            shape = RoundedCornerShape(RoundMedium),
        ) {
            Text("Back")
        }
    }
}
