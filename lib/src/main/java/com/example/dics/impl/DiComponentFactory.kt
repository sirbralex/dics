package com.example.dics.impl

import com.example.dics.component.DiComponent
import com.example.dics.component.WithKeyOnlyDiComponent
import com.example.dics.graph.ComponentKey

interface DiComponentFactory<A : DiComponent> {
    fun createComponent(provider: DiComponentProvider): A
}

interface DiComponentWithKeyFactory<C : WithKeyOnlyDiComponent, K : ComponentKey<C>> {
    fun createComponent(provider: DiComponentProvider, componentKey: K): C
}
