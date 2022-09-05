package com.example.dics.graph

import com.example.dics.component.DiComponent
import com.example.dics.contextimpl.DiContextImpl

internal sealed class Node {
    val dependencies: MutableSet<ComponentNode> = mutableSetOf()
}

internal class ComponentNode(
    val componentKey: ComponentKey<*>,
    val component: DiComponent
) : Node() {
    val consumers: MutableSet<Node> = mutableSetOf()
}

internal class LeafNode(
    val leafNodeKey: LeafNodeKey,
    val consumer: DiContextImpl
) : Node()
