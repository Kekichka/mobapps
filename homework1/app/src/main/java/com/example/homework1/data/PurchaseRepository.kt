package com.example.homework1.data

class PurchaseRepository {
    fun getPurchases(count: Int): List<Purchase> {
        return randomPurchases(count)
    }
}
