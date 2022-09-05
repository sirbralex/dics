package com.example.dics.component

import com.example.dics.impl.DiComponentFactory
import com.example.dics.impl.DiComponentProvider

class Feature_0_Component : ApplicationDiComponent {

    class Factory : DiComponentFactory<Feature_0_Component> {
        override fun createComponent(provider: DiComponentProvider): Feature_0_Component {
            return Feature_0_Component()
        }
    }
}
