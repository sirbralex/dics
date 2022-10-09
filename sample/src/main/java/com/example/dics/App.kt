package com.example.dics

import android.app.Application
import com.example.dics.component.*
import com.example.dics.context.diContext
import com.example.dics.impl.configure

class App : Application() {

    override fun onCreate() {
        diContext.configure {
            registerComponentFactory(Feature_0_Component.Factory())
            registerComponentFactory(Feature_1_Component.Factory())
            registerComponentFactory(Feature_2_Component.Factory())
            registerComponentFactory(Feature_3_Component.Factory())
            registerComponentFactory(Feature_4_Component.Factory())
            registerComponentFactory(ActivityComponent.Factory())
            registerComponentFactory(ActivityPermanentComponent.Factory())
            registerComponentFactory(FragmentComponent.Factory())
        }
        super.onCreate()
    }

}
