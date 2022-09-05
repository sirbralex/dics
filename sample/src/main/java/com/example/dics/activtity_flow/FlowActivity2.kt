package com.example.dics.activtity_flow

import android.content.Intent
import android.os.Bundle
import com.example.dics.base.BaseActivity
import com.example.dics.context.diContext
import com.example.dics.contextimpl.obtainComponent
import com.example.dics.component.Feature_1_Component

class FlowActivity2 : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goBtn.setOnClickListener {
            startActivity(Intent(this, FlowActivity3::class.java))
            finish()
        }
        diContext.obtainComponent(Feature_1_Component::class)
    }
}
