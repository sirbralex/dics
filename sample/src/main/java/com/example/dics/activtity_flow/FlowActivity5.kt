package com.example.dics.activtity_flow

import android.os.Bundle
import com.example.dics.base.BaseActivity

class FlowActivity5 : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goBtn.setOnClickListener {
            finish()
        }
    }
}
