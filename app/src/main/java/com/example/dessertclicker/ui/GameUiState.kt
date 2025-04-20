package com.example.dessertclicker.ui

import androidx.annotation.DrawableRes

data class GameUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    @DrawableRes val currentDessertImageId: Int,
    val currentDessertPrice: Int
)