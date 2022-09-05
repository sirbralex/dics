package com.example.dics.impl

import com.example.dics.component.DiComponent
import com.example.dics.graph.ComponentKey
import kotlin.reflect.KClass

interface DiComponentProvider {
    fun <A : DiComponent> obtainComponent(kClass: KClass<A>): A
    fun <A : DiComponent> obtainComponent(componentKey: ComponentKey<A>): A
}
