package com.example.dics.contextimpl

import com.example.dics.component.DiComponent
import com.example.dics.impl.DiComponentFactoryWrapper
import kotlin.reflect.KClass

internal fun <A : DiComponent> DiContextImpl.getComponentFactory(kClass: KClass<A>): DiComponentFactoryWrapper<A, *> {
    val factory = internalGetComponentFactory(kClass)
    if (factory == null) {
        throw IllegalStateException("No factory registered for $kClass")
    }
    return factory
}

private fun <A : DiComponent> DiContextImpl.internalGetComponentFactory(kClass: KClass<A>): DiComponentFactoryWrapper<A, *>? {
    val factory = factories[kClass]
    if (factory != null) {
        @Suppress("UNCHECKED_CAST")
        return factory as DiComponentFactoryWrapper<A, *>
    }
    delegates.forEach { delegate ->
        delegate.internalGetComponentFactory(kClass)?.let { factory ->
            return factory
        }
    }
    return null
}
