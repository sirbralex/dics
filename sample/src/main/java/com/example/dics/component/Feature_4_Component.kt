package com.example.dics.component

import com.example.dics.graph.ComponentKey
import com.example.dics.impl.DiComponentProvider
import com.example.dics.impl.DiComponentWithKeyFactory

class Feature_4_Component(val name: String) : ActivityDiComponent, WithKeyOnlyDiComponent {

    class Factory : DiComponentWithKeyFactory<Feature_4_Component, Feature4ComponentKey> {
        override fun createComponent(
            provider: DiComponentProvider,
            componentKey: Feature4ComponentKey
        ): Feature_4_Component {
            return Feature_4_Component(componentKey.name)
        }
    }

    data class Feature4ComponentKey(val name: String) :
        ComponentKey<Feature_4_Component>(Feature_4_Component::class)
}
