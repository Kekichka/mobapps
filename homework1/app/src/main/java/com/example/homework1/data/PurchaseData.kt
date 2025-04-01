package com.example.homework1.data

import androidx.compose.ui.graphics.Color
import com.example.homework1.R
import kotlin.random.Random
import com.example.homework1.ui.res.*

data class Purchase(
    val iconRes: Int,
    val title: String,
    val date: String,
    val amount: String,
    val color: Color
)

fun randomPurchases(count: Int): List<Purchase> {
    val categories = listOf("Buy Camera" to R.drawable.camera, "Buy TV" to R.drawable.tv)
    val colors = listOf(blue, pink, yellow, lightblue)

    return List(count) {
        val (title, icon) = categories.random()
        val price = Random.nextInt(100, 2000)
        Purchase(
            iconRes = icon,
            title = title,
            date = "02/11/2018",
            amount = "- $$price",
            color = colors.random()
        )
    }
}
