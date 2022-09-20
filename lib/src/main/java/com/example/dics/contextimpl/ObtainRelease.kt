package com.example.dics.contextimpl

import attachConsumerToDependency
import detachConsumerFromDependency
import com.example.dics.component.DiComponent
import com.example.dics.context.DiContext
import com.example.dics.graph.ComponentKey
import com.example.dics.graph.ComponentNode
import com.example.dics.graph.LeafNode
import com.example.dics.graph.LeafNodeKey
import com.example.dics.log.log
import kotlin.reflect.KClass

fun <A : DiComponent> DiContext.obtainComponent(kClass: KClass<A>): A {
    val componentKey = ComponentKey(kClass)
    return obtainComponent(componentKey)
}

fun <A : DiComponent> DiContext.obtainComponent(componentKey: ComponentKey<A>): A {
    this as DiContextImpl
    return obtainComponent(componentKey)
}

@Suppress("UNCHECKED_CAST")
internal fun <A : DiComponent> DiContextImpl.obtainComponent(
    componentKey: ComponentKey<A>
): A {
    val componentNode = componentNodeProvider.getComponentNode(this, componentKey)
    val leafNodeKey = LeafNodeKey(this)
    var leafNode = graph.getNode(leafNodeKey)
    if (leafNode == null) {
        leafNode = LeafNode(leafNodeKey, this)
    }

    graph.addNode(leafNodeKey, leafNode)
    attachConsumerToDependency(leafNode, componentNode)

    return componentNode.component as A
}

internal fun DiContextImpl.releaseComponent() {
    val leafNodeKey = LeafNodeKey(this)
    val leafNode = graph.getNode(leafNodeKey)

    if (leafNode != null) {
        // Avoid ConcurrentModificationException
        val dependencies = HashSet(leafNode.dependencies)
        dependencies.forEach { dependency ->
            detachConsumerFromDependency(leafNode, dependency)
            if (dependency.consumers.isEmpty()) {
                tryRemoveComponentNode(this, dependency)
            }
        }
        graph.removeNode(leafNodeKey)
    }
}

private fun tryRemoveComponentNode(consumerDiContext: DiContextImpl, componentNode: ComponentNode) {
    val ownerContext = consumerDiContext.getOwnerOf(componentNode.componentKey.kClass)
    if (componentNode.consumers.isEmpty()) {
        ownerContext.graph.removeNode(componentNode.componentKey)
        log("component removed ${componentNode.componentKey.kClass}")

        componentNode.dependencies.forEach {
            detachConsumerFromDependency(componentNode, it)
            tryRemoveComponentNode(ownerContext, it)
        }
    }
}
