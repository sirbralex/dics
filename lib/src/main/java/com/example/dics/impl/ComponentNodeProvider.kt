package com.example.dics.impl

import attachConsumerToDependency
import com.example.dics.component.DiComponent
import com.example.dics.component.PermanentDiComponent
import com.example.dics.contextimpl.DiContextImpl
import com.example.dics.contextimpl.getOwnerOf
import com.example.dics.graph.*
import com.example.dics.graph.ComponentNode
import com.example.dics.graph.LeafNode
import com.example.dics.graph.LeafNodeKey

internal class ComponentNodeProvider {
    fun getComponentNode(diContextImpl: DiContextImpl, componentNodeKey: ComponentNodeKey<out DiComponent>): ComponentNode {
        val ownerContext = diContextImpl.getOwnerOf(componentNodeKey.kClass)
        val existingNode = ownerContext.graph.getNode(componentNodeKey) as? ComponentNode?
        if (existingNode != null) {
            return existingNode
        }
        val res = ownerContext.componentNodeFactory.createComponentNode(ownerContext, componentNodeKey)
        ownerContext.graph.addNode(componentNodeKey, res.node)

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
