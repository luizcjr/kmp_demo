package com.example.kmpdemo.android.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kmpdemo.android.ui.screens.product.ProductsScreen
import com.example.kmpdemo.android.ui.screens.state.error.ErrorScreen
import com.example.kmpdemo.android.ui.screens.state.loading.Loading
import com.example.kmpdemo.state.RequestState

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = viewModel()
    val requestState by viewModel.requestState

    when (requestState) {
        is RequestState.Loading -> Loading()
        is RequestState.Success -> ProductsScreen((requestState as RequestState.Success).data.items)
        is RequestState.Error -> ErrorScreen((requestState as RequestState.Error).message)
        RequestState.Idle -> Unit
    }
}