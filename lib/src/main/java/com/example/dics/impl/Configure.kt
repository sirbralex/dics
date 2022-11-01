package com.example.dics.impl

import com.example.dics.component.DiComponent
import com.example.dics.component.WithKeyOnlyDiComponent
import com.example.dics.context.DiContext
import com.example.dics.contextimpl.DiContextImpl
import com.example.dics.graph.ComponentKey
import kotlin.reflect.KClass

fun DiContext.configure(configureFun: DiContextConfig.() -> Unit) {
    val diContextConfig = DiContextConfig(this as DiContextImpl)
    configureFun(diContextConfig)
}

class DiContextConfig internal constructor(private val diContextImpl: DiContextImpl) {
    fun <C : DiComponent> registerComponentFactory(
        kClass: KClass<C>,
        factory: DiComponentFactory<C>
    ) {
        diContextImpl.addComponentFactory(
            kClass,
            DiComponentFactoryWrapperImpl<C, WithKeyOnlyDiComponent, ComponentKey<WithKeyOnlyDiComponent>>(
                factory
            )
        )
    }

    inline fun <reified A : DiComponent> registerComponentFactory(factory: DiComponentFactory<A>) {
        return registerComponentFactory(A::class, factory)
    }

    fun <C : WithKeyOnlyDiComponent, K : ComponentKey<C>> registerComponentFactory(
        kClass: KClass<C>,
        factory: DiComponentWithKeyFactory<C, K>
    ) {
        diContextImpl.addComponentFactory(
            kClass,
            DiComponentFactoryWrapperImpl<DiComponent, C, K>(factory)
        )
    }

    inline fun <reified A : WithKeyOnlyDiComponent, K : ComponentKey<A>> registerComponentFactory(
        factory: DiComponentWithKeyFactory<A, K>
    ) {
        return registerComponentFactory(A::class, factory)
    }

    fun setOwnerOf(vararg kClass: KClass<out DiComponent>) {
        diContextImpl.addAssociatedComponents(*kClass)
    }
}
