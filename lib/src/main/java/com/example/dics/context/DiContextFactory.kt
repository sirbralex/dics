package com.example.dics.context

import com.example.dics.component.DiComponent
import com.example.dics.contextimpl.DiContextImplFactory
import kotlin.reflect.KClass

interface DiContextFactory<T : ClearableDiContext> {
    fun createDiContext(name: String, associatedComponent: KClass<out DiComponent>, delegates: List<T>): T
}

var diContextFactory: DiContextFactory<*>? = DiContextImplFactory()

fun <T : ClearableDiContext> createDiContext(
    name: String,
    associatedComponent: KClass<out DiComponent>,
    vararg delegates: DiContext
): T {
    @Suppress("UNCHECKED_CAST")
    return (diContextFactory as DiContextFactory<T>).createDiContext(
        name,
        associatedComponent,
        delegates.asList() as List<T>
    )
}
