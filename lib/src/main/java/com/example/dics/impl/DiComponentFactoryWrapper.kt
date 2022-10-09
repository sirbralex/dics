package com.example.dics.impl

import com.example.dics.component.DiComponent
import com.example.dics.graph.ComponentKey

internal class DiComponentFactoryWrapper<A : DiComponent, K : ComponentKey<A>> {

    private val diComponentWithKeyFactory: DiComponentWithKeyFactory<A, K>?
    private val diComponentFactory: DiComponentFactory<A>?

    constructor(ff: DiComponentWithKeyFactory<A, K>) {
        this.diComponentWithKeyFactory = ff
        this.diComponentFactory = null
    }

    constructor(ff2: DiComponentFactory<A>) {
        this.diComponentWithKeyFactory = null
        this.diComponentFactory = ff2
    }

    fun createComponent(provider: DiComponentProvider, componentKey: ComponentKey<*>): A {
        if (diComponentWithKeyFactory == null) {
            return diComponentFactory!!.createComponent(provider)
        } else {
            @Suppress("UNCHECKED_CAST")
            return diComponentWithKeyFactory.createComponent(provider, componentKey as K)
        }
    }
}
