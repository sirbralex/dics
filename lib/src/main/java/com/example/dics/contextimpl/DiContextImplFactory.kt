package com.example.dics.contextimpl

import com.example.dics.component.DiComponent
import com.example.dics.context.DiContextFactory
import com.example.dics.impl.ComponentNodeFactory
import com.example.dics.impl.ComponentNodeProvider
import kotlin.reflect.KClass

internal class DiContextImplFactory : DiContextFactory<DiContextImpl> {

    private val diGraphComponentNodeProvider = ComponentNodeProvider()
    private val diGraphComponentNodeFactory = ComponentNodeFactory()

    override fun createDiContext(
        name: String,
        associatedComponent: KClass<out DiComponent>,
        delegates: List<DiContextImpl>
    ): DiContextImpl {
        return DiContextImpl(
            componentNodeProvider = diGraphComponentNodeProvider,
            componentNodeFactory = diGraphComponentNodeFactory,
            name = name,
            delegates = delegates,
            associatedComponent = associatedComponent
        )
    }
}
