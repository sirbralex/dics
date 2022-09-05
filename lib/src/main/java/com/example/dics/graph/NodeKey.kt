package com.example.dics.graph

import com.example.dics.component.DiComponent
import com.example.dics.contextimpl.DiContextImpl
import kotlin.reflect.KClass

internal sealed interface NodeKey
internal data class LeafNodeKey(val consumer: DiContextImpl) : NodeKey
data class ComponentKey<A : DiComponent>(val kClass: KClass<A>) : NodeKey
