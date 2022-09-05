package com.example.dics.component

import com.example.dics.impl.DiComponentFactory
import com.example.dics.impl.DiComponentProvider

class ActivityPermanentComponent : ActivityPermanentDiComponent {

    class Factory : DiComponentFactory<ActivityPermanentComponent> {
        override fun createComponent(provider: DiComponentProvider): ActivityPermanentComponent {
            return ActivityPermanentComponent()
        }
    }
}
