package com.example.dics.fragment_flow

import android.os.Bundle
import android.view.View
import com.example.dics.R
import com.example.dics.base.BaseFragment
import com.example.dics.context.diContext
import com.example.dics.contextimpl.obtainComponent
import com.example.dics.component.Feature_1_Component

class FlowFragment1 : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        diContext.obtainComponent(Feature_1_Component::class)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, FlowFragment2())
                .commit()
        }
    }
}
