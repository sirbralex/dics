package com.example.dics.activtity_flow

import android.content.Intent
import android.os.Bundle
import com.example.dics.base.BaseActivity
import com.example.dics.context.diContext
import com.example.dics.contextimpl.obtainComponent
import com.example.dics.component.Feature_1_Component

class FlowActivity1 : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goBtn.setOnClickListener {
            startActivity(Intent(this, FlowActivity2::class.java))
            finish()
        }
        diContext.obtainComponent(Feature_1_Component::class)
    }
}
