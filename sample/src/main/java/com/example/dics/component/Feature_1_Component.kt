package com.example.dics.component

import com.example.dics.impl.DiComponentFactory
import com.example.dics.impl.DiComponentProvider

class Feature_1_Component : ApplicationDiComponent {

    class Factory : DiComponentFactory<Feature_1_Component> {
        override fun createComponent(provider: DiComponentProvider): Feature_1_Component {
            provider.obtainComponent(Feature_0_Component::class)
            return Feature_1_Component()
        }
    }
}
