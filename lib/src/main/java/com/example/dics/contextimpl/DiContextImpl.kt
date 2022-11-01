package com.example.dics.contextimpl

import com.example.dics.component.DiComponent
import com.example.dics.context.ClearableDiContext
import com.example.dics.graph.ComponentKey
import com.example.dics.graph.Graph
import com.example.dics.impl.ComponentNodeFactory
import com.example.dics.impl.ComponentNodeProvider
import com.example.dics.impl.DiComponentFactoryWrapper
import com.example.dics.impl.DiComponentFactoryWrapperImpl
import kotlin.reflect.KClass

internal class DiContextImpl(
    val componentNodeProvider: ComponentNodeProvider,
    val componentNodeFactory: ComponentNodeFactory,
    val name: String,
    val delegates: List<DiContextImpl>,
    associatedComponent: KClass<out DiComponent>
) : ClearableDiContext {

    val graph = Graph()

    private val _factories = mutableMapOf<KClass<out DiComponent>, DiComponentFactoryWrapper>()
    val factories: Map<KClass<out DiComponent>, DiComponentFactoryWrapper>
        get() = _factories

    private val _associatedComponents: MutableSet<KClass<out DiComponent>> = mutableSetOf()
    val associatedComponents: Set<KClass<out DiComponent>>
        get() = _associatedComponents

    init {
        _associatedComponents.add(associatedComponent)
    }

    override fun onCleared() {
        releaseComponent()
    }

    override fun toString(): String {
        return name
    }

    fun <A : DiComponent> addComponentFactory(
        kClass: KClass<A>,
        factory: DiComponentFactoryWrapper
    ) {
        _factories[kClass] = factory
    }

    fun addAssociatedComponents(vararg kClass: KClass<out DiComponent>) {
        _associatedComponents.addAll(kClass)
    }
}

