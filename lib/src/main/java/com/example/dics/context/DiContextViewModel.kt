package com.example.dics.context

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

internal class DiContextViewModel : ViewModel() {
    var nonConfigDiContext: ClearableDiContext? = null
    var ownerDiContext: ClearableDiContext? = null
    var viewDiContext: ClearableDiContext? = null

    lateinit var onClearedCallback: () -> Unit

    override fun onCleared() {
        onClearedCallback.invoke()
    }
}

internal class DiContextViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DiContextViewModel() as T
    }
}
