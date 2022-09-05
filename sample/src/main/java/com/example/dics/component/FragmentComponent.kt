package com.example.dics.component

import com.example.dics.impl.DiComponentFactory
import com.example.dics.impl.DiComponentProvider

class FragmentComponent : FragmentDiComponent {

    class Factory : DiComponentFactory<FragmentComponent> {
        override fun createComponent(provider: DiComponentProvider): FragmentComponent {
            return FragmentComponent()
        }
    }
}
