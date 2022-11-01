package com.example.dics.impl

import com.example.dics.component.DiComponent
import com.example.dics.contextimpl.DiContextImpl
import com.example.dics.contextimpl.getComponentFactory
import com.example.dics.graph.ComponentNode
import com.example.dics.graph.ComponentNodeKey

internal class ComponentNodeFactory {
    fun createComponentNode(
        diContextImpl: DiContextImpl,
        componentNodeKey: ComponentNodeKey<out DiComponent>
    ): Result {
        val componentProvider = DiComponentProviderImpl(diContextImpl)
        val component = diContextImpl.getComponentFactory(componentNodeKey.kClass).createComponent(
            componentProvider,
            componentNodeKey.componentKey
        )
        return Result(
            node = ComponentNode(componentNodeKey, component),
            obtainedNodes = componentProvider.obtainedComponentNodes
        )
    }

    class Result(
        val node: ComponentNode,
        val obtainedNodes: List<ComponentNode>
    )
}
