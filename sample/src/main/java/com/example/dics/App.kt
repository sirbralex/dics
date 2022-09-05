package com.example.dics

import android.app.Application
import com.example.dics.context.diContext
import com.example.dics.component.ActivityComponent
import com.example.dics.component.ActivityPermanentComponent
import com.example.dics.component.Feature_0_Component
import com.example.dics.component.Feature_1_Component
import com.example.dics.component.Feature_2_Component
import com.example.dics.component.Feature_3_Component
import com.example.dics.component.FragmentComponent
import com.example.dics.impl.configure

class App : Application() {

    override fun onCreate() {
        diContext.configure {
            registerComponentFactory(Feature_0_Component.Factory())
            registerComponentFactory(Feature_1_Component.Factory())
            registerComponentFactory(Feature_2_Component.Factory())
            registerComponentFactory(Feature_3_Component.Factory())
            registerComponentFactory(ActivityComponent.Factory())
            registerComponentFactory(ActivityPermanentComponent.Factory())
            registerComponentFactory(FragmentComponent.Factory())
        }
        super.onCreate()
    }

}
