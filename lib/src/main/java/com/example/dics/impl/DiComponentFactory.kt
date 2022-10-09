package com.example.dics.impl

import com.example.dics.component.DiComponent
import com.example.dics.graph.ComponentKey

interface DiComponentFactory<A : DiComponent> {
    fun createComponent(provider: DiComponentProvider): A
}

interface DiComponentWithKeyFactory<A : DiComponent, K : ComponentKey<A>> {
    fun createComponent(provider: DiComponentProvider, componentKey: K): A
}
