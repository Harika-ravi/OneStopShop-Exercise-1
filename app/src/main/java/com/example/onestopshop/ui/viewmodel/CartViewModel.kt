package com.example.onestopshop.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.onestopshop.data.CartItem
import com.example.onestopshop.data.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CartViewModel : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    fun itemCount(): Int = _cartItems.value.sumOf { it.quantity }
    fun subtotal(): Double = _cartItems.value.sumOf { it.product.price * it.quantity }
    fun tax(): Double = subtotal() * 0.13
    fun total(): Double = subtotal() + tax()

    fun isInCart(productId: Int): Boolean =
        _cartItems.value.any { it.product.id == productId }

    fun quantityInCart(productId: Int): Int =
        _cartItems.value.find { it.product.id == productId }?.quantity ?: 0

    fun addToCart(product: Product) {
        _cartItems.update { list ->
            val existing = list.find { it.product.id == product.id }
            if (existing != null)
                list.map { if (it.product.id == product.id) it.copy(quantity = it.quantity + 1) else it }
            else
                list + CartItem(product = product, quantity = 1)
        }
    }

    fun increaseQuantity(productId: Int) {
        _cartItems.update { list ->
            list.map { if (it.product.id == productId) it.copy(quantity = it.quantity + 1) else it }
        }
    }

    fun decreaseQuantity(productId: Int) {
        _cartItems.update { list ->
            val item = list.find { it.product.id == productId } ?: return@update list
            if (item.quantity <= 1) list.filter { it.product.id != productId }
            else list.map { if (it.product.id == productId) it.copy(quantity = it.quantity - 1) else it }
        }
    }

    fun removeFromCart(productId: Int) {
        _cartItems.update { list -> list.filter { it.product.id != productId } }
    }

    fun clearCart() { _cartItems.value = emptyList() }
}