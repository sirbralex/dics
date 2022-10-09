package com.example.dics.component_key

import android.os.Bundle
import android.view.View
import com.example.dics.R
import com.example.dics.base.BaseFragment
import com.example.dics.component.Feature_4_Component
import com.example.dics.context.diContext
import com.example.dics.contextimpl.obtainComponent
import com.example.dics.log.log

class KeyFragment1 : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val key = Feature_4_Component.Feature4ComponentKey("instance 1")
        val component = diContext.obtainComponent(key)
        log("FlowFragment1 component name = ${component.name}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                    .replace(R.id.container, KeyFragment2())
                    .commit()
        }
    }
}
