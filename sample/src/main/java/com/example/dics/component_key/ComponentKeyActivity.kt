package com.example.dics.component_key

import android.os.Bundle
import androidx.core.view.isVisible
import com.example.dics.R
import com.example.dics.base.BaseActivity

class ComponentKeyActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goBtn.isVisible = false
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, KeyFragment1())
                .commit()
    }
}
