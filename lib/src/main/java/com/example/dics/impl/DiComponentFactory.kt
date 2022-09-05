package com.example.dics.impl

import com.example.dics.component.DiComponent

interface DiComponentFactory<A : DiComponent> {
    fun createComponent(provider: DiComponentProvider): A
}
