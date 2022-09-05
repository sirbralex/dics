package com.example.dics.impl

import com.example.dics.contextimpl.DiContextImpl
import com.example.dics.contextimpl.getComponentFactory
import com.example.dics.graph.ComponentKey
import com.example.dics.graph.ComponentNode

internal class ComponentNodeFactory {
    fun createComponentNode(
        diContextImpl: DiContextImpl,
        componentKey: ComponentKey<*>
    ): Result {
        val componentProvider = DiComponentProviderImpl(diContextImpl)
        val component = diContextImpl.getComponentFactory(componentKey.kClass).createComponent(componentProvider)
        return Result(
            node = ComponentNode(componentKey, component),
            obtainedNodes = componentProvider.obtainedComponentNodes
        )
    }

    class Result(
        val node: ComponentNode,
        val obtainedNodes: List<ComponentNode>
    )
}
