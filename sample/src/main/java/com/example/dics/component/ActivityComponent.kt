package com.example.dics.component

import com.example.dics.impl.DiComponentFactory
import com.example.dics.impl.DiComponentProvider

class ActivityComponent : ActivityDiComponent {

    class Factory : DiComponentFactory<ActivityComponent> {
        override fun createComponent(provider: DiComponentProvider): ActivityComponent {
            return ActivityComponent()
        }
    }
}
