package com.example.dics.contextimpl

import attachConsumerToDependency
import detachConsumerFromDependency
import com.example.dics.component.DiComponent
import com.example.dics.component.WithKeyOnlyDiComponent
import com.example.dics.context.DiContext
import com.example.dics.graph.*
import com.example.dics.graph.ComponentNode
import com.example.dics.graph.LeafNode
import com.example.dics.graph.LeafNodeKey
import com.example.dics.log.log
import kotlin.reflect.KClass

fun <A : DiComponent> DiContext.obtainComponent(kClass: KClass<A>): A {
    this as DiContextImpl
    val componentNodeKey = ComponentNodeKey(kClass)
    return obtainComponent(componentNodeKey)
}

fun <A : WithKeyOnlyDiComponent> DiContext.obtainComponent(kClass: KClass<A>): Unit {
    log("not provided")
}

fun <C : WithKeyOnlyDiComponent> DiContext.obtainComponent(componentKey: ComponentKey<C>): C {
    this as DiContextImpl
    val componentNodeKey = ComponentNodeKey(componentKey.kClass, componentKey)
    return obtainComponent(componentNodeKey)
}

@Suppress("UNCHECKED_CAST")
internal fun <A : DiComponent> DiContextImpl.obtainComponent(
    componentNodeKey: ComponentNodeKey<A>
): A {
    val componentNode = componentNodeProvider.getComponentNode(this, componentNodeKey)
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
    val ownerContext = consumerDiContext.getOwnerOf(componentNode.nodeKey.kClass)
    if (componentNode.consumers.isEmpty()) {
        ownerContext.graph.removeNode(componentNode.nodeKey)
        log("component removed ${componentNode.nodeKey.kClass}")

        componentNode.dependencies.forEach {
            detachConsumerFromDependency(componentNode, it)
            tryRemoveComponentNode(ownerContext, it)
        }
    }
}
