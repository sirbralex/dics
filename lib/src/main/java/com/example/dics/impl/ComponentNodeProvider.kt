package com.example.dics.impl

import attachConsumerToDependency
import com.example.dics.component.PermanentDiComponent
import com.example.dics.contextimpl.DiContextImpl
import com.example.dics.contextimpl.getOwnerOf
import com.example.dics.graph.ComponentKey
import com.example.dics.graph.ComponentNode
import com.example.dics.graph.LeafNode
import com.example.dics.graph.LeafNodeKey

internal class ComponentNodeProvider {
    fun getComponentNode(diContextImpl: DiContextImpl, componentKey: ComponentKey<*>): ComponentNode {
        val ownerContext = diContextImpl.getOwnerOf(componentKey.kClass)
        val existingNode = ownerContext.graph.getNode(componentKey) as? ComponentNode?
        if (existingNode != null) {
            return existingNode
        }
        val res = ownerContext.componentNodeFactory.createComponentNode(ownerContext, componentKey)
        ownerContext.graph.addNode(componentKey, res.node)

        if (res.node.component is PermanentDiComponent) {
            // Hack to support permanent component
            val leafNodeKey = LeafNodeKey(ownerContext)
            var leafNode = ownerContext.graph.getNode(leafNodeKey)
            if (leafNode == null) {
                leafNode = LeafNode(leafNodeKey, ownerContext)
            }
            ownerContext.graph.addNode(leafNodeKey, leafNode)
            attachConsumerToDependency(leafNode, res.node)
        }

        res.obtainedNodes.forEach { dependency ->
            attachConsumerToDependency(res.node, dependency)
        }
        return res.node
    }
}
