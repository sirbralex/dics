package com.example.dics.weak_ref_di_context

import android.os.Bundle
import android.os.Handler
import com.example.dics.base.BaseActivity
import com.example.dics.component.Feature_1_Component
import com.example.dics.component.Feature_2_Component
import com.example.dics.context.cleanupWeakDiContexts
import com.example.dics.context.weakRefDiContext
import com.example.dics.contextimpl.obtainComponent

class WeakRefDiContextActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goBtn.setOnClickListener {
            click1()
            goBtn.setOnClickListener {
                click2()
                goBtn.setOnClickListener {
                    click3()
                    goBtn.setOnClickListener {
                        click4()
                    }
                }
            }
        }
    }

    private fun click1() {
        Array(40_000_000) { 0 }.weakRefDiContext.obtainComponent(Feature_1_Component::class)
        goBtn.isEnabled = false
        Handler().postDelayed({ System.gc() }, 2000)
        Handler().postDelayed({ goBtn.isEnabled = true }, 5000)
    }

    private fun click2() {
        Array(40_000_000) { 0 }.weakRefDiContext.obtainComponent(Feature_2_Component::class)
        goBtn.isEnabled = false
        Handler().postDelayed({ System.gc() }, 2000)
        Handler().postDelayed({ goBtn.isEnabled = true }, 5000)
    }

    private fun click3() {
        cleanupWeakDiContexts()
    }

    private fun click4() {
        finish()
    }
}
