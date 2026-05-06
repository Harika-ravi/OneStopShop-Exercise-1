package com.example.onestopshop.data

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String,
    val gender: String,
    val isNew: Boolean = false,
    val isSale: Boolean = false,
    val originalPrice: Double? = null,
    val badge: String? = null
)

data class CartItem(
    val product: Product,
    val quantity: Int = 1
)