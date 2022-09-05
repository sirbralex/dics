package com.example.dics.component

import com.example.dics.impl.DiComponentFactory
import com.example.dics.impl.DiComponentProvider

class Feature_2_Component : ApplicationPermanentDiComponent {

    class Factory : DiComponentFactory<Feature_2_Component> {
        override fun createComponent(provider: DiComponentProvider): Feature_2_Component {
            provider.obtainComponent(Feature_0_Component::class)
            return Feature_2_Component()
        }
    }
}
