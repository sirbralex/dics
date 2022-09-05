package com.example.dics.permanent_component

import android.os.Bundle
import android.view.View
import com.example.dics.R
import com.example.dics.base.BaseFragment
import com.example.dics.context.diContext
import com.example.dics.contextimpl.obtainComponent
import com.example.dics.component.ActivityComponent
import com.example.dics.component.ActivityPermanentComponent

class Level_2_1_Fragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        diContext.obtainComponent(ActivityComponent::class)
        diContext.obtainComponent(ActivityPermanentComponent::class)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, Level_2_2_Fragment())
                .commit()
        }
    }
}
