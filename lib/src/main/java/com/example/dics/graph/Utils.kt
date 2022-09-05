import com.example.dics.graph.ComponentNode
import com.example.dics.graph.Node

internal fun attachConsumerToDependency(consumer: Node, dependency: ComponentNode) {
    dependency.consumers.add(consumer)
    consumer.dependencies.add(dependency)
}

internal fun detachConsumerFromDependency(consumer: Node, dependency: ComponentNode) {
    dependency.consumers.remove(consumer)
    consumer.dependencies.remove(dependency)
}
