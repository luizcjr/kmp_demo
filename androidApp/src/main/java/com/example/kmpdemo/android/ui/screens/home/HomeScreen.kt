package com.example.kmpdemo.android.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kmpdemo.android.ui.screens.product.ProductsScreen
import com.example.kmpdemo.android.ui.screens.state.error.ErrorScreen
import com.example.kmpdemo.android.ui.screens.state.loading.Loading

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = viewModel()
    val requestState by viewModel.requestState

    when {
        requestState.isLoading() -> Loading()
        requestState.isSuccess() -> ProductsScreen(requestState.getProducts().items)
        requestState.isError() -> ErrorScreen(requestState.getErrorMessage())
    }
}