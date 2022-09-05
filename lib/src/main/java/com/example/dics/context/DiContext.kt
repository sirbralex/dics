package com.example.dics.context

sealed interface DiContext

interface ClearableDiContext : DiContext {
    fun onCleared()
}
