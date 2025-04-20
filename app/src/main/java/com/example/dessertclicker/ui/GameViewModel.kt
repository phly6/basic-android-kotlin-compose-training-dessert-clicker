// DessertClickerViewModel.kt
package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert

class DessertClickerViewModel : ViewModel() {
    private val desserts = Datasource.dessertList

    // Backing state for the UI
    var uiState by mutableStateOf(
        GameUiState(
            currentDessertImageId = desserts.first().imageId,
            currentDessertPrice   = desserts.first().price
        )
    )
        private set

    /** Called when the user taps the dessert image. */
    fun onDessertClicked() {
        val newDessertsSold = uiState.dessertsSold + 1
        val newRevenue      = uiState.revenue + uiState.currentDessertPrice
        val nextDessert = pickDessertFor(newDessertsSold)

        // Update state atomically
        uiState = uiState.copy(
            revenue                = newRevenue,
            dessertsSold           = newDessertsSold,
            currentDessertImageId  = nextDessert.imageId,
            currentDessertPrice    = nextDessert.price
        )
    }

    private fun pickDessertFor(count: Int): Dessert{
        var choice = desserts.first()
        for (d in desserts){
            if (count >= d.startProductionAmount) choice = d
            else break
        }
        return choice
    }
}
