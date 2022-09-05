package com.example.dics.fragment_hierarchy

import com.example.dics.component.FragmentDiComponent
import com.example.dics.impl.DiComponentFactory
import com.example.dics.impl.DiComponentProvider

class Level_0_FragmentComponent : FragmentDiComponent {

    class Factory : DiComponentFactory<Level_0_FragmentComponent> {
        override fun createComponent(provider: DiComponentProvider): Level_0_FragmentComponent {
            return Level_0_FragmentComponent()
        }
    }
}
