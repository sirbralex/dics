package com.example.dics.impl

import com.example.dics.component.DiComponent
import com.example.dics.component.WithKeyOnlyDiComponent
import com.example.dics.graph.ComponentKey

internal interface DiComponentFactoryWrapper {
    fun createComponent(
        provider: DiComponentProvider,
        componentKey: ComponentKey<*>?
    ): DiComponent
}

internal class DiComponentFactoryWrapperImpl<C1 : DiComponent, C2 : WithKeyOnlyDiComponent, K : ComponentKey<C2>> :
    DiComponentFactoryWrapper {

    private val diComponentWithKeyFactory: DiComponentWithKeyFactory<C2, K>?
    private val diComponentFactory: DiComponentFactory<C1>?

    constructor(ff: DiComponentWithKeyFactory<C2, K>) {
        this.diComponentWithKeyFactory = ff
        this.diComponentFactory = null
    }

    constructor(ff2: DiComponentFactory<C1>) {
        this.diComponentWithKeyFactory = null
        this.diComponentFactory = ff2
    }

    override fun createComponent(
        provider: DiComponentProvider,
        componentKey: ComponentKey<*>?
    ): DiComponent {
        if (diComponentWithKeyFactory == null) {
            return diComponentFactory!!.createComponent(provider)
        } else {
            return diComponentWithKeyFactory.createComponent(provider, componentKey as K)
        }
    }
}
