package com.example.dics.component

import com.example.dics.impl.DiComponentFactory
import com.example.dics.impl.DiComponentProvider

class Feature_3_Component : ApplicationPermanentDiComponent {

    class Factory : DiComponentFactory<Feature_3_Component> {
        override fun createComponent(provider: DiComponentProvider): Feature_3_Component {
            return Feature_3_Component()
        }
    }
}
