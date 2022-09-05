package com.example.dics.fragment_hierarchy

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.dics.R
import com.example.dics.base.BaseFragment
import com.example.dics.context.diContext
import com.example.dics.contextimpl.obtainComponent
import com.example.dics.impl.configure

class Level_0_Fragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        diContext.configure {
            registerComponentFactory(Level_0_FragmentComponent.Factory())
            setOwnerOf(Level_0_FragmentComponent::class)
        }
        super.onCreate(savedInstanceState)
        diContext.obtainComponent(Level_0_FragmentComponent::class)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goBtn.isVisible = false
        childFragmentManager.beginTransaction()
            .add(R.id.container, Level_1_Fragment())
            .commit()
    }
}
