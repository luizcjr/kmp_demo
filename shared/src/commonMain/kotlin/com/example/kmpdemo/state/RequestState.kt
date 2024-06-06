package com.example.kmpdemo.state

import com.example.kmpdemo.model.Products
import kotlinx.serialization.Serializable

@Serializable
sealed class RequestState {
    @Serializable
    data object Idle : RequestState()
    @Serializable
    data object Loading : RequestState()
    @Serializable
    data class Success(val data: Products) : RequestState()
    @Serializable
    data class Error(val message: String) : RequestState()
}