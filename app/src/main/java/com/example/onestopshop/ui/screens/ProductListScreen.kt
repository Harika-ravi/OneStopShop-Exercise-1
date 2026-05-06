package com.example.onestopshop.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.onestopshop.data.Product
import com.example.onestopshop.data.ProductRepository
import com.example.onestopshop.ui.theme.*
import com.example.onestopshop.ui.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    cartViewModel: CartViewModel,
    onOpenCart: () -> Unit
) {
    val cartItems by cartViewModel.cartItems.collectAsState()
    val cartCount = cartItems.sumOf { it.quantity }
    var selectedFilter by remember { mutableStateOf("ALL") }
    val filters = listOf("ALL", "WOMEN", "MEN", "NEW", "SALE")

    val filtered = when (selectedFilter) {
        "WOMEN" -> ProductRepository.products.filter { it.gender == "Women" }
        "MEN"   -> ProductRepository.products.filter { it.gender == "Men" }
        "NEW"   -> ProductRepository.products.filter { it.isNew }
        "SALE"  -> ProductRepository.products.filter { it.isSale }
        else    -> ProductRepository.products
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text          = "ONE STOP SHOP",
                            fontSize      = 16.sp,
                            fontWeight    = FontWeight.Thin,
                            letterSpacing = 6.sp
                        )
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Outlined.Search, contentDescription = "Search")
                        }
                        IconButton(onClick = onOpenCart) {
                            BadgedBox(
                                badge = {
                                    if (cartCount > 0) {
                                        Badge(containerColor = LuxeGold) {
                                            Text(
                                                text       = cartCount.toString(),
                                                color      = Color.Black,
                                                fontSize   = 9.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }
                            ) {
                                Icon(Icons.Outlined.ShoppingBag, contentDescription = "Cart")
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor         = MaterialTheme.colorScheme.background,
                        titleContentColor      = MaterialTheme.colorScheme.onBackground,
                        actionIconContentColor = MaterialTheme.colorScheme.onBackground
                    )
                )

                // Filter tabs
                ScrollableTabRow(
                    selectedTabIndex = filters.indexOf(selectedFilter).coerceAtLeast(0),
                    containerColor   = MaterialTheme.colorScheme.background,
                    contentColor     = LuxeGold,
                    edgePadding      = 12.dp,
                    divider = {
                        HorizontalDivider(
                            color     = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.08f),
                            thickness = 0.5.dp
                        )
                    }
                ) {
                    filters.forEach { filter ->
                        Tab(
                            selected               = selectedFilter == filter,
                            onClick                = { selectedFilter = filter },
                            selectedContentColor   = LuxeGold,
                            unselectedContentColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.45f),
                            text = {
                                Text(
                                    text          = filter,
                                    fontSize      = 10.sp,
                                    letterSpacing = 2.sp,
                                    fontWeight    = if (selectedFilter == filter) FontWeight.Medium
                                    else FontWeight.Normal
                                )
                            }
                        )
                    }
                }

                // Item count row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment     = Alignment.CenterVertically
                ) {
                    Text(
                        text          = "${filtered.size} ITEMS",
                        fontSize      = 10.sp,
                        letterSpacing = 1.5.sp,
                        color         = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                    )
                    Row(
                        verticalAlignment     = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            Icons.Outlined.FilterList,
                            contentDescription = "Filter",
                            modifier           = Modifier.size(14.dp),
                            tint               = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                        )
                        Text(
                            text          = "FILTER & SORT",
                            fontSize      = 10.sp,
                            letterSpacing = 1.5.sp,
                            color         = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                        )
                    }
                }
            }
        }
    ) { padding ->
        LazyVerticalGrid(
            columns               = GridCells.Fixed(2),
            modifier              = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background),
            contentPadding        = PaddingValues(horizontal = 10.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement   = Arrangement.spacedBy(14.dp)
        ) {
            items(filtered, key = { it.id }) { product ->
                ProductCard(
                    product  = product,
                    isInCart = cartViewModel.isInCart(product.id),
                    quantity = cartViewModel.quantityInCart(product.id),
                    onAdd    = { cartViewModel.addToCart(product) }
                )
            }
            item { Spacer(Modifier.height(16.dp)) }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun ProductCard(
    product  : Product,
    isInCart : Boolean,
    quantity : Int,
    onAdd    : () -> Unit
) {
    Card(
        modifier  = Modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(0.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors    = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.72f)
            ) {
                AsyncImage(
                    model              = product.imageUrl,
                    contentDescription = product.name,
                    modifier           = Modifier.fillMaxSize(),
                    contentScale       = ContentScale.Crop
                )

                // Badge
                product.badge?.let { badge ->
                    val bg = when (badge) {
                        "SALE"      -> LuxeRed
                        "NEW"       -> LuxeBlack
                        "EXCLUSIVE" -> LuxeGold
                        else        -> LuxeDarkGray
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp)
                            .background(bg)
                    ) {
                        Text(
                            text          = badge,
                            modifier      = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            color         = Color.White,
                            fontSize      = 7.sp,
                            fontWeight    = FontWeight.Medium,
                            letterSpacing = 1.5.sp
                        )
                    }
                }

                // Wishlist
                IconButton(
                    onClick  = {},
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(2.dp)
                        .size(34.dp)
                ) {
                    Icon(
                        Icons.Outlined.FavoriteBorder,
                        contentDescription = "Wishlist",
                        tint               = Color.White,
                        modifier           = Modifier.size(18.dp)
                    )
                }

                // Add to bag overlay
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = if (isInCart)
                                    listOf(LuxeGold.copy(alpha = 0.85f), LuxeGold.copy(alpha = 0.97f))
                                else
                                    listOf(Color(0xAA000000), Color(0xCC000000))
                            )
                        )
                        .clickable { onAdd() }
                        .padding(vertical = 9.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment     = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector        = if (isInCart) Icons.Filled.Check else Icons.Filled.Add,
                            contentDescription = null,
                            tint               = Color.White,
                            modifier           = Modifier.size(13.dp)
                        )
                        Text(
                            text          = if (isInCart) "IN BAG  ·  $quantity" else "ADD TO BAG",
                            color         = Color.White,
                            fontSize      = 8.sp,
                            fontWeight    = FontWeight.Medium,
                            letterSpacing = 1.5.sp
                        )
                    }
                }
            }

            // Product info
            Column(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                Text(
                    text          = product.gender.uppercase(),
                    fontSize      = 7.sp,
                    letterSpacing = 1.5.sp,
                    color         = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.35f),
                    fontWeight    = FontWeight.Medium
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    text       = product.name,
                    fontSize   = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color      = MaterialTheme.colorScheme.onSurface,
                    maxLines   = 2,
                    overflow   = TextOverflow.Ellipsis,
                    lineHeight = 18.sp
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text       = product.description,
                    fontSize   = 9.sp,
                    color      = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f),
                    maxLines   = 2,
                    overflow   = TextOverflow.Ellipsis,
                    lineHeight = 13.sp
                )
                Spacer(Modifier.height(10.dp))
                Row(
                    verticalAlignment     = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text       = "CAD ${"%.2f".format(product.price)}",
                        fontSize   = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color      = if (product.isSale) LuxeRed
                        else MaterialTheme.colorScheme.onSurface
                    )
                    product.originalPrice?.let {
                        Text(
                            text           = "CAD ${"%.2f".format(it)}",
                            fontSize       = 10.sp,
                            color          = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.35f),
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                }
            }
        }
    }
}