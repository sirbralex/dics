package com.example.dics.graph

internal class Graph {

    val nodes = mutableMapOf<NodeKey, Node>()

    fun addNode(key: NodeKey, node: Node) {
        nodes[key] = node
    }

    fun removeNode(key: NodeKey) {
        nodes.remove(key)
    }

    fun getNode(key: NodeKey): Node? {
        return nodes[key]
    }

    fun getNodes(): Set<Node> {
        return HashSet(nodes.values)
    }

}
