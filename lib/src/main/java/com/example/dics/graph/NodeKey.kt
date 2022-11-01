package com.example.dics.graph

import com.example.dics.component.DiComponent
import com.example.dics.component.WithKeyOnlyDiComponent
import com.example.dics.contextimpl.DiContextImpl
import kotlin.reflect.KClass

internal sealed interface NodeKey
internal data class LeafNodeKey(val consumer: DiContextImpl) : NodeKey
internal data class ComponentNodeKey<C : DiComponent>(
    val kClass: KClass<C>,
    val componentKey: ComponentKey<*>? = null
) : NodeKey


open class ComponentKey<C : WithKeyOnlyDiComponent>(val kClass: KClass<C>)
