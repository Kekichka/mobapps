package com.example.homework2.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.homework2.R
import com.example.homework2.viewmodel.NoteViewModel
import com.example.homework2.ui.theme.*

@Composable
fun AddNoteScreen(navController: NavController, viewModel: NoteViewModel) {
    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(SmallPadding))

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Text") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
                .clickable{
                    if (title.isNotBlank() && text.isNotBlank()) {
                        viewModel.addNote(title, text)
                        navController.popBackStack()
                    } else {
                        Toast.makeText(context, "Oopsie!! Write something pls", Toast.LENGTH_SHORT).show()
                    }
                }
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.heart),
                contentDescription = "Heart Button",
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(
                text = "Save",
                color = White,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(MediumPadding))


        FloatingActionButton(
            onClick = { navController.popBackStack() },
            containerColor = PookieGreen,
            contentColor = White,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(100.dp),
            shape = RoundedCornerShape(RoundMedium)
        ) {
            Text("Back")
        }
    }
}
