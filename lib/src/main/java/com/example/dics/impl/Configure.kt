package com.example.dics.impl

import com.example.dics.component.DiComponent
import com.example.dics.context.DiContext
import com.example.dics.contextimpl.DiContextImpl
import kotlin.reflect.KClass

fun DiContext.configure(configureFun: DiContextConfig.() -> Unit) {
    val diContextConfig = DiContextConfig(this as DiContextImpl)
    configureFun(diContextConfig)
}

class DiContextConfig internal constructor(private val diContextImpl: DiContextImpl) {
    fun <A : DiComponent> registerComponentFactory(kClass: KClass<A>, factory: DiComponentFactory<A>) {
        diContextImpl.addComponentFactory(kClass, factory)
    }

    inline fun <reified A : DiComponent> registerComponentFactory(factory: DiComponentFactory<A>) {
        return registerComponentFactory(A::class, factory)
    }

    fun setOwnerOf(vararg kClass: KClass<out DiComponent>) {
        diContextImpl.addAssociatedComponents(*kClass)
    }
}
