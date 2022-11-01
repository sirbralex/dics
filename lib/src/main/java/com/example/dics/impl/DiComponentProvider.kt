package com.example.dics.impl

import com.example.dics.component.DiComponent
import com.example.dics.component.WithKeyOnlyDiComponent
import com.example.dics.graph.ComponentKey
import kotlin.reflect.KClass

interface DiComponentProvider {
    fun <C : DiComponent> obtainComponent(kClass: KClass<C>): C
    fun <C : WithKeyOnlyDiComponent> obtainComponent(kClass: KClass<C>) = Unit
    fun <C : WithKeyOnlyDiComponent> obtainComponent(componentKey: ComponentKey<C>): C
}
