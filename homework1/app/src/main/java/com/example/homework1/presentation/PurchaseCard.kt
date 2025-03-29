package com.example.homework1.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.clip
import com.example.homework1.data.Purchase
import androidx.compose.ui.platform.LocalContext

@Composable
fun PurchaseCard(purchase: Purchase) {

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(16.dp)
            .clickable {
                Toast.makeText(context, "${purchase.title} ${purchase.amount}", Toast.LENGTH_SHORT).show()
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(purchase.color, shape = RoundedCornerShape(13.dp))
                    .padding(13.dp)
            ) {
                Image(
                    painter = painterResource(id = purchase.iconRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = purchase.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = purchase.date, color = Color.Gray, fontSize = 14.sp)
            }
        }
        Text(
            text = purchase.amount,
            color = Color(0xFFFF4267),
            fontSize = 16.sp
        )
    }
}
