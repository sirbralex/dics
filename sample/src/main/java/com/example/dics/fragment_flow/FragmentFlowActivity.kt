package com.example.dics.fragment_flow

import android.os.Bundle
import androidx.core.view.isVisible
import com.example.dics.R
import com.example.dics.base.BaseActivity
import com.example.dics.context.diContext
import com.example.dics.contextimpl.obtainComponent
import com.example.dics.component.Feature_3_Component
import com.example.dics.fragment_flow.FlowFragment1

class FragmentFlowActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        diContext.obtainComponent(Feature_3_Component::class)
        goBtn.isVisible = false
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FlowFragment1())
            .commit()
    }
}
