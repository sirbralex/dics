package com.example.dics.impl

import com.example.dics.component.DiComponent
import com.example.dics.contextimpl.DiContextImpl
import com.example.dics.graph.ComponentKey
import com.example.dics.graph.ComponentNode
import kotlin.reflect.KClass

internal class DiComponentProviderImpl(
    private val diContextImpl: DiContextImpl
) : DiComponentProvider {

    var obtainedComponentNodes = mutableListOf<ComponentNode>()

    override fun <A : DiComponent> obtainComponent(kClass: KClass<A>): A {
        val componentKey = ComponentKey(kClass)
        return obtainComponent(componentKey)
    }

    override fun <A : DiComponent> obtainComponent(componentKey: ComponentKey<A>): A {
        val node = diContextImpl.componentNodeProvider.getComponentNode(diContextImpl, componentKey)
        obtainedComponentNodes.add(node)
        @Suppress("UNCHECKED_CAST")
        return node.component as A
    }
}
