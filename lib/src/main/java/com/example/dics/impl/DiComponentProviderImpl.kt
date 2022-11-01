package com.example.dics.impl

import com.example.dics.component.DiComponent
import com.example.dics.component.WithKeyOnlyDiComponent
import com.example.dics.contextimpl.DiContextImpl
import com.example.dics.graph.ComponentKey
import com.example.dics.graph.ComponentNode
import com.example.dics.graph.ComponentNodeKey
import kotlin.reflect.KClass

internal class DiComponentProviderImpl(
    private val diContextImpl: DiContextImpl
) : DiComponentProvider {

    var obtainedComponentNodes = mutableListOf<ComponentNode>()

    override fun <A : DiComponent> obtainComponent(kClass: KClass<A>): A {
        val componentNodeKey = ComponentNodeKey(kClass)
        val node = diContextImpl.componentNodeProvider.getComponentNode(diContextImpl, componentNodeKey)
        obtainedComponentNodes.add(node)
        @Suppress("UNCHECKED_CAST")
        return node.component as A
    }

    override fun <A : WithKeyOnlyDiComponent> obtainComponent(componentKey: ComponentKey<A>): A {
        val componentNodeKey = ComponentNodeKey(componentKey.kClass, componentKey)
        val node = diContextImpl.componentNodeProvider.getComponentNode(diContextImpl, componentNodeKey)
        obtainedComponentNodes.add(node)
        @Suppress("UNCHECKED_CAST")
        return node.component as A
    }
}
