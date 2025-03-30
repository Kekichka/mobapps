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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import com.example.homework1.data.Purchase
import com.example.homework1.data.OTPgen
import com.example.homework1.ui.res.*

@Composable
fun Check(purchases: List<Purchase>) {
    val totalSum = purchases.sumOf {
        val amountString = it.amount.replace(Regex("[^0-9]"), "")
        amountString.toIntOrNull() ?: 0
    }

    var otp by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(true) }
    var isPayClicked by remember { mutableStateOf(false) }
    val isPayEnabled = otp.isNotEmpty() && !isPayClicked

    val context = LocalContext.current
    val OTPgen = OTPgen()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(RoundMedium))
            .background(white)
            .padding(MediumPadding)
    ) {
        LazyColumn(
            modifier = Modifier.height(450.dp),
            contentPadding = PaddingValues(bottom = LargePadding)
        ) {
            items(purchases) { purchase ->
                PurchaseCard(purchase)
                HorizontalDivider(
                    color = grey,
                    thickness = DividerThickness,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(SmallPadding))
            }
        }

        Spacer(modifier = Modifier.height(MediumPadding))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "TOTAL",
                fontSize = 18.sp,
                color = black,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = pink,
                            fontWeight = FontWeight.Bold,
                        )
                    ) {
                        append("-$$totalSum")
                    }
                },
                fontSize = 25.sp,
                color = black,
            )
        }

        Spacer(modifier = Modifier.height(MediumPadding))
    }

    DropdownMenu()

    Text(
        text = "Get OTP to verify transaction",
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        color = darkgrey,
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(SmallPadding)
    ) {
        Box(
            modifier = Modifier
                .weight(0.6f)
                .height(ButtonHeight)
                .clip(RoundedCornerShape(RoundSmall))
                .background(Color.Transparent)
                .border(1.dp, darkgrey, RoundedCornerShape(RoundMedium))
                .wrapContentHeight(Alignment.CenterVertically)
                .padding(horizontal = MediumPadding),
        ) {
            Text(
                text = otp.ifEmpty { "OTP" },
                color = if (otp.isEmpty()) darkgrey else black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .weight(0.4f)
                .height(ButtonHeight)
                .clip(RoundedCornerShape(RoundMedium))
                .background(if (isButtonEnabled) blue else grey)
                .wrapContentHeight(Alignment.CenterVertically)
                .clickable(enabled = isButtonEnabled) {
                    otp = OTPgen.generateOtp()
                    isButtonEnabled = false
                }
                .padding(horizontal = MediumPadding),
        ) {
            Text(
                text = "Get OTP",
                color = white,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    Spacer(modifier = Modifier.height(MediumPadding))

    Row {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(ButtonHeight)
                .clip(RoundedCornerShape(RoundMedium))
                .background(if (isPayEnabled) blue else grey)
                .clickable(enabled = isPayEnabled) {
                    Toast.makeText(context, "Yayy!! youve spent $totalSum! work harder to spend more ;)", Toast.LENGTH_SHORT).show()
                    isPayClicked = true
                }
                .padding(horizontal = MediumPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "PAY",
                color = white,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}
