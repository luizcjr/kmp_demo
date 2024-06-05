package com.example.kmpdemo.android.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmpdemo.model.Product

@Composable
fun ProductsScreen(products: List<Product>) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(
            items = products,
            key = { it.id }
        ) {
            ProductCard(product = it)
        }
    }
}