package com.example.onestopshop.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.onestopshop.data.CartItem
import com.example.onestopshop.ui.theme.*
import com.example.onestopshop.ui.viewmodel.CartViewModel
import androidx.compose.runtime.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    cartViewModel: CartViewModel,
    onBack: () -> Unit
) {
    val cartItems by cartViewModel.cartItems.collectAsState()
    val subtotal  = cartViewModel.subtotal()
    val tax       = cartViewModel.tax()
    val total     = cartViewModel.total()
    val itemCount = cartViewModel.itemCount()
    var showSuccess by remember { mutableStateOf(false) }

    if (showSuccess) {
        OrderSuccessDialog {
            showSuccess = false
            cartViewModel.clearCart()
            onBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                title = {
                    Column {
                        Text(
                            text          = "MY BAG",
                            fontSize      = 13.sp,
                            fontWeight    = FontWeight.Light,
                            letterSpacing = 4.sp
                        )
                        if (itemCount > 0) {
                            Text(
                                text          = "$itemCount ITEM${if (itemCount != 1) "S" else ""}",
                                fontSize      = 9.sp,
                                letterSpacing = 1.5.sp,
                                color         = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.45f)
                            )
                        }
                    }
                },
                actions = {
                    if (cartItems.isNotEmpty()) {
                        TextButton(onClick = { cartViewModel.clearCart() }) {
                            Text(
                                text          = "CLEAR ALL",
                                fontSize      = 9.sp,
                                letterSpacing = 1.5.sp,
                                color         = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.45f)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        bottomBar = {
            if (cartItems.isNotEmpty()) {
                CartSummaryBar(
                    subtotal   = subtotal,
                    tax        = tax,
                    total      = total,
                    itemCount  = itemCount,
                    onCheckout = { showSuccess = true }
                )
            }
        }
    ) { padding ->
        if (cartItems.isEmpty()) {
            EmptyCartView(
                modifier  = Modifier.padding(padding),
                onShopNow = onBack
            )
        } else {
            LazyColumn(
                modifier       = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(MaterialTheme.colorScheme.background),
                contentPadding = PaddingValues(bottom = 8.dp)
            ) {
                items(cartItems, key = { it.product.id }) { item ->
                    CartItemRow(
                        cartItem   = item,
                        onIncrease = { cartViewModel.increaseQuantity(item.product.id) },
                        onDecrease = { cartViewModel.decreaseQuantity(item.product.id) },
                        onRemove   = { cartViewModel.removeFromCart(item.product.id) }
                    )
                    HorizontalDivider(
                        modifier  = Modifier.padding(horizontal = 16.dp),
                        color     = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.07f),
                        thickness = 0.5.dp
                    )
                }
            }
        }
    }
}

@Composable
fun CartItemRow(
    cartItem   : CartItem,
    onIncrease : () -> Unit,
    onDecrease : () -> Unit,
    onRemove   : () -> Unit
) {
    Row(
        modifier              = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Box(modifier = Modifier.size(width = 96.dp, height = 126.dp)) {
            AsyncImage(
                model              = cartItem.product.imageUrl,
                contentDescription = cartItem.product.name,
                modifier           = Modifier.fillMaxSize(),
                contentScale       = ContentScale.Crop
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text          = cartItem.product.gender.uppercase(),
                fontSize      = 8.sp,
                letterSpacing = 1.5.sp,
                color         = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.35f)
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text       = cartItem.product.name,
                fontSize   = 14.sp,
                fontWeight = FontWeight.Normal,
                maxLines   = 2,
                overflow   = TextOverflow.Ellipsis,
                lineHeight = 20.sp
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text     = "CAD ${"%.2f".format(cartItem.product.price)} / item",
                fontSize = 11.sp,
                color    = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            )
            Spacer(Modifier.height(14.dp))

            Row(
                modifier              = Modifier.fillMaxWidth(),
                verticalAlignment     = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier          = Modifier.border(
                        0.5.dp,
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.18f),
                        RoundedCornerShape(0.dp)
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick  = onDecrease,
                        modifier = Modifier.size(34.dp)
                    ) {
                        Icon(
                            Icons.Filled.Remove,
                            contentDescription = "Decrease",
                            modifier           = Modifier.size(13.dp)
                        )
                    }
                    Text(
                        text       = cartItem.quantity.toString(),
                        modifier   = Modifier.widthIn(min = 28.dp),
                        fontSize   = 14.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign  = TextAlign.Center
                    )
                    IconButton(
                        onClick  = onIncrease,
                        modifier = Modifier.size(34.dp)
                    ) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = "Increase",
                            modifier           = Modifier.size(13.dp)
                        )
                    }
                }

                Text(
                    text       = "CAD ${"%.2f".format(cartItem.product.price * cartItem.quantity)}",
                    fontSize   = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        IconButton(
            onClick  = onRemove,
            modifier = Modifier
                .size(28.dp)
                .align(Alignment.Top)
        ) {
            Icon(
                Icons.Outlined.Close,
                contentDescription = "Remove",
                modifier           = Modifier.size(15.dp),
                tint               = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.35f)
            )
        }
    }
}

@Composable
fun CartSummaryBar(
    subtotal  : Double,
    tax       : Double,
    total     : Double,
    itemCount : Int,
    onCheckout: () -> Unit
) {
    Surface(
        color           = MaterialTheme.colorScheme.background,
        shadowElevation = 12.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            SummaryRow(
                label = "SUBTOTAL ($itemCount item${if (itemCount != 1) "s" else ""})",
                value = "CAD ${"%.2f".format(subtotal)}"
            )
            Spacer(Modifier.height(6.dp))
            SummaryRow(label = "ESTIMATED SHIPPING", value = "FREE")
            Spacer(Modifier.height(6.dp))
            SummaryRow(
                label = "HST / TAX (13%)",
                value = "CAD ${"%.2f".format(tax)}"
            )
            Spacer(Modifier.height(12.dp))
            HorizontalDivider(
                color     = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.10f),
                thickness = 0.5.dp
            )
            Spacer(Modifier.height(12.dp))

            Row(
                modifier              = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment     = Alignment.CenterVertically
            ) {
                Text(
                    text          = "ORDER TOTAL",
                    fontSize      = 13.sp,
                    fontWeight    = FontWeight.Medium,
                    letterSpacing = 2.sp
                )
                Text(
                    text       = "CAD ${"%.2f".format(total)}",
                    fontSize   = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick   = onCheckout,
                modifier  = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape     = RoundedCornerShape(0.dp),
                colors    = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onBackground,
                    contentColor   = MaterialTheme.colorScheme.background
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Text(
                    text          = "PROCEED TO CHECKOUT",
                    fontSize      = 11.sp,
                    fontWeight    = FontWeight.Medium,
                    letterSpacing = 3.sp
                )
            }

            Spacer(Modifier.height(6.dp))
            Text(
                text          = "Secure checkout  ·  Free returns  ·  Free shipping over \$150",
                modifier      = Modifier.fillMaxWidth(),
                fontSize      = 9.sp,
                color         = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.35f),
                textAlign     = TextAlign.Center,
                letterSpacing = 0.3.sp
            )
        }
    }
}

@Composable
private fun SummaryRow(label: String, value: String) {
    Row(
        modifier              = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Text(
            text          = label,
            fontSize      = 10.sp,
            letterSpacing = 1.2.sp,
            color         = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.50f)
        )
        Text(
            text       = value,
            fontSize   = 12.sp,
            fontWeight = if (value == "FREE") FontWeight.Normal else FontWeight.Medium,
            color      = if (value == "FREE") LuxeGreen
            else MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun EmptyCartView(
    modifier  : Modifier = Modifier,
    onShopNow : () -> Unit
) {
    Column(
        modifier              = modifier
            .fillMaxSize()
            .padding(40.dp),
        verticalArrangement   = Arrangement.Center,
        horizontalAlignment   = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Outlined.ShoppingBag,
            contentDescription = null,
            modifier           = Modifier.size(72.dp),
            tint               = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.15f)
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text          = "YOUR BAG IS EMPTY",
            fontSize      = 14.sp,
            letterSpacing = 3.sp,
            fontWeight    = FontWeight.Light,
            color         = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.45f)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text          = "Add items to get started",
            fontSize      = 12.sp,
            color         = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.28f),
            letterSpacing = 0.3.sp
        )
        Spacer(Modifier.height(36.dp))
        OutlinedButton(
            onClick  = onShopNow,
            shape    = RoundedCornerShape(0.dp),
            border   = androidx.compose.foundation.BorderStroke(
                0.5.dp,
                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.35f)
            )
        ) {
            Text(
                text          = "CONTINUE SHOPPING",
                fontSize      = 10.sp,
                letterSpacing = 2.sp,
                color         = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f)
            )
        }
    }
}

@Composable
fun OrderSuccessDialog(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties       = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.88f)
                .wrapContentHeight(),
            shape    = RoundedCornerShape(0.dp),
            color    = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier            = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Outlined.CheckCircle,
                    contentDescription = null,
                    modifier           = Modifier.size(52.dp),
                    tint               = LuxeGold
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    text          = "ORDER PLACED",
                    fontSize      = 18.sp,
                    fontWeight    = FontWeight.Light,
                    letterSpacing = 4.sp
                )
                Spacer(Modifier.height(12.dp))
                HorizontalDivider(
                    modifier  = Modifier.width(40.dp),
                    color     = LuxeGold,
                    thickness = 1.dp
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text       = "Thank you for your purchase.\nYour order has been confirmed\nand will ship within 2–4 business days.",
                    fontSize   = 13.sp,
                    color      = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.55f),
                    textAlign  = TextAlign.Center,
                    lineHeight = 21.sp
                )
                Spacer(Modifier.height(28.dp))
                Button(
                    onClick   = onDismiss,
                    modifier  = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape     = RoundedCornerShape(0.dp),
                    colors    = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onBackground,
                        contentColor   = MaterialTheme.colorScheme.background
                    ),
                    elevation = ButtonDefaults.buttonElevation(0.dp)
                ) {
                    Text(
                        text          = "CONTINUE SHOPPING",
                        fontSize      = 10.sp,
                        letterSpacing = 2.5.sp
                    )
                }
            }
        }
    }
}