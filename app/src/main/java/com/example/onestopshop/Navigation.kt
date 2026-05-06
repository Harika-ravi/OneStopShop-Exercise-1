package com.example.onestopshop

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.onestopshop.ui.screens.CartScreen
import com.example.onestopshop.ui.screens.ProductListScreen
import com.example.onestopshop.ui.screens.SplashScreen
import com.example.onestopshop.ui.viewmodel.CartViewModel

sealed class Screen(val route: String) {
    object Splash      : Screen("splash")
    object ProductList : Screen("products")
    object Cart        : Screen("cart")
}

@Composable
fun OneStopShopNavHost(
    navController: NavHostController,
    cartViewModel: CartViewModel = viewModel()
) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(onShopNow = { navController.navigate(Screen.ProductList.route) })
        }
        composable(Screen.ProductList.route) {
            ProductListScreen(
                cartViewModel = cartViewModel,
                onOpenCart    = { navController.navigate(Screen.Cart.route) }
            )
        }
        composable(Screen.Cart.route) {
            CartScreen(
                cartViewModel = cartViewModel,
                onBack        = { navController.popBackStack() }
            )
        }
    }
}