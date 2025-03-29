package com.example.homework1.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import com.example.homework1.data.Purchase

@Composable
fun Check(purchases: List<Purchase>) {
    val totalSum = purchases.sumOf {
        val amountString = it.amount.replace(Regex("[^0-9]"), "")
        amountString.toIntOrNull() ?: 0
    }

    var otp by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(true) }
    val isPayEnabled = otp.isNotEmpty()

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.height(450.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(purchases) { purchase ->
                PurchaseCard(purchase)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "TOTAL",
                fontSize = 18.sp,
                color = Color.Black
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFFFF4267),
                            fontWeight = FontWeight.Bold,
                        )
                    ) {
                        append("-$$totalSum")
                    }
                },
                fontSize = 25.sp,
                color = Color.Black,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

    }

    DropdownMenu()

    Text(
        text = "Get OTP to verify transaction",
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        color = Color.Gray,
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(0.6f)
                .height(48.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Transparent)
                .border(1.dp, Color.Gray, RoundedCornerShape(20.dp))
                .wrapContentHeight(Alignment.CenterVertically)
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = otp.ifEmpty { "OTP" },
                color = if (otp.isEmpty()) Color.Gray else Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .weight(0.4f)
                .height(50.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(if (isButtonEnabled) Color(0xFF3629B7) else Color.Gray)
                .wrapContentHeight(Alignment.CenterVertically)
                .clickable(enabled = isButtonEnabled) {
                    (100000..999999).random().toString()
                    isButtonEnabled = false
                }
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = "Get OTP",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Row {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(48.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(if (isPayEnabled) Color(0xFF3629B7) else Color(0xFFF2F1F9))
                .clickable(enabled = isPayEnabled) {
                    Toast.makeText(context, "Yayy!! youve spent $totalSum! work harder to spend more ;)", Toast.LENGTH_SHORT).show()
                }
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "PAY",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}
